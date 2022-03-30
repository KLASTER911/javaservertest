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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;


@Transactional
@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class Resources {
    @Autowired NodesRepository nodesRepository;

    @GetMapping("/nodes/")
    public @ResponseBody Iterable<Nodes> getInfoAllNodes(){
        System.out.println("GET /nodes");
        return nodesRepository.findAll();
    } 

    @PostMapping("/nodes/")
    public ResponseEntity<Nodes> addNewNode(@RequestBody Nodes body){
        System.out.println("POST /nodes");
        Nodes saveNodeInDataBase = nodesRepository.save(body);
        return ResponseEntity.status(HttpStatus.OK).body(saveNodeInDataBase);
    }

    @GetMapping("/nodes/{nodeId}")
    public ResponseEntity<Object> getInfoOneNode(@PathVariable Integer nodeId){
        System.out.println("GET /nodes/{nodeId}");
        Optional<Nodes> findNodeById = nodesRepository.findById(nodeId);
        if (findNodeById.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(findNodeById);
    }

    @PutMapping("/nodes/{nodeId}")
    public ResponseEntity<Object> updateInfoOneNode(@PathVariable Integer nodeId, @RequestBody Nodes body) {
        System.out.println("PUT /nodes/{nodeId}");
        Optional<Nodes> findNodeById = nodesRepository.findById(nodeId);
        if (findNodeById.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        findNodeById.get().setId(body.getId());
        findNodeById.get().setIp(body.getIp());
        findNodeById.get().setName(body.getName());
        findNodeById.get().setParentId(body.getParentId());
        findNodeById.get().setPort(body.getPort());
        return ResponseEntity.status(HttpStatus.OK).body(findNodeById);
    }

    @DeleteMapping("/nodes/{nodeId}")
    public ResponseEntity<Object> deleteInfoOneNode(@PathVariable Integer nodeId) {
        System.out.println("DELETE /nodes/{nodeId}");
        Optional<Nodes> findNodeById = nodesRepository.findById(nodeId);
        if (findNodeById.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        nodesRepository.deleteById(nodeId); // удаление ноды
        nodesRepository.deleteByParentId(nodeId); // удаление дочерних нод
        return ResponseEntity.ok().build();
    }

    @GetMapping("/nodes/{nodeId}/children")
    public ResponseEntity<Object> getNodeChildren(@PathVariable Integer nodeId){
        System.out.println("GET /nodes/{nodeId}/children/");
        Iterable<Nodes> findAllNodesByParentId = nodesRepository.findAllByParentId(nodeId);
        return ResponseEntity.status(HttpStatus.OK).body(findAllNodesByParentId);
    }

    @GetMapping("/nodes/root")
    public ResponseEntity<Object> getRootNodes(){
        System.out.println("GET /nodes/root");
        Iterable<Nodes> findAllNodesByParentId = nodesRepository.findAllByParentId(null);
        return ResponseEntity.status(HttpStatus.OK).body(findAllNodesByParentId);
    }
}
