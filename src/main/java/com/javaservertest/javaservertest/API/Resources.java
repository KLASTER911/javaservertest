package com.javaservertest.javaservertest.API;
import com.javaservertest.javaservertest.DataBase.NodesRepository;

import java.util.Optional;

import com.javaservertest.javaservertest.DataBase.Nodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;


@Transactional
@RestController
@RequestMapping("/v1")
public class Resources {
    @Autowired NodesRepository nodesRepository;

    @GetMapping("/nodes")
    public @ResponseBody Iterable<Nodes> getInfoAllNodes(){
        System.out.println("GET /nodes");
        return nodesRepository.findAll();
    } 

    @PostMapping("/nodes")
    public ResponseEntity<Nodes> addNewNode(@RequestBody Nodes body){
        System.out.println("POST /nodes");
        Nodes save = nodesRepository.save(body);
        return ResponseEntity.status(HttpStatus.OK).body(save);
    }

    @GetMapping("/nodes/{nodeId}")
    public ResponseEntity<Object> getInfoOneNode(@PathVariable Integer nodeId){
        System.out.println("GET /nodes/{nodeId}");
        Optional<Nodes> nodesById = nodesRepository.findById(nodeId);
        if (nodesById.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(nodesById);
    }

    @PutMapping("/nodes/{nodeId}")
    public ResponseEntity<Object> updateInfoOneNode(@PathVariable Integer nodeId, @RequestBody Nodes body) {
        System.out.println("PUT /nodes/{nodeId}");
        Optional<Nodes> nodesById = nodesRepository.findById(nodeId);
        if (nodesById.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        nodesById.get().setId(body.getId());
        nodesById.get().setIp(body.getIp());
        nodesById.get().setName(body.getName());
        nodesById.get().setParentId(body.getParentId());
        nodesById.get().setPort(body.getPort());
        // судя по описанию put в сваггер - нужно удаление по nodeId и вставка новых данных под этим ID
        // "nodeId - ID of node that needs to de deleted"
        // сделал через удаление, но лучше было бы через UPDATE
        return ResponseEntity.status(HttpStatus.OK).body(nodesById);
    }

    @DeleteMapping("/nodes/{nodeId}")
    public ResponseEntity<Object> deleteInfoOneNode(@PathVariable Integer nodeId) {
        System.out.println("DELETE /nodes/{nodeId}");
        Optional<Nodes> nodesById = nodesRepository.findById(nodeId);
        if (nodesById.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        nodesRepository.deleteById(nodeId); // удаление ноды
        nodesRepository.deleteByParentId(nodeId); // удаление дочерних нод
        return ResponseEntity.ok().build();
    }

    @GetMapping("/nodes/{nodeId}/children")
    public ResponseEntity<Object> getNodeChildren(@PathVariable Integer nodeId){
        System.out.println("GET /nodes/{nodeId}/children/");
        Iterable<Nodes> nodesById = nodesRepository.findAllByParentId(nodeId);
        return ResponseEntity.status(HttpStatus.OK).body(nodesById);
    }

    @GetMapping("/nodes/root")
    public ResponseEntity<Object> getRootNodes(){
        System.out.println("GET /nodes/root");
        Iterable<Nodes> nodesById = nodesRepository.findAllByParentId(null);
        return ResponseEntity.status(HttpStatus.OK).body(nodesById);
    }
}
