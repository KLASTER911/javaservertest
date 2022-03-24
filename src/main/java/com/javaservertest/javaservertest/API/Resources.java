package com.javaservertest.javaservertest.API;
import com.javaservertest.javaservertest.DataBase.NodesRepository;
import com.javaservertest.javaservertest.DataBase.Nodes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
public class Resources {
    @Autowired NodesRepository nodesRepository;

    @GetMapping("/nodes")
    public @ResponseBody Iterable<Nodes> getInfoAllNodes(){
        System.out.println("GET /nodes");
        return nodesRepository.findAll();
    } 

    @PostMapping("/nodes")
    public ResponseEntity<String> addNewNode(@RequestBody(required = false) String body){
        System.out.println("POST /nodes");
        Nodes nodes = new Nodes();
        nodes.setIp("1232"); 
        nodes.setName("aa");
        nodes.setParentId(0); 
        nodes.setPort(123);
        System.out.println(nodes.getId() + nodes.getIp() + nodes.getName() + nodes.getParentId() + nodes.getPort());
        nodesRepository.save(nodes);
        return ResponseEntity.status(HttpStatus.CREATED).body("Created");
    }

    @GetMapping("/nodes/{id}")
    public void getInfoOneNode(){
        System.out.println("GET /nodes/{nodeId}");
    }

    @PutMapping(value="/nodes/{nodeId}")
    public void updateInfoOneNode(@PathVariable String nodeId, @RequestBody(required = false) String entity) {
        System.out.println("PUT /nodes/{nodeId}");
    }

    @DeleteMapping(value="/nodes/{nodeId}")
    public void deleteInfoOneNode(@PathVariable String nodeId) {
        System.out.println("DELETE /nodes/{nodeId}");
    }

    @GetMapping("/nodes/{nodeId}/children/")
    public void getNodeChildren(@PathVariable String nodeId){
        System.out.println("GET /nodes/{nodeId}/children/");
    }

    @GetMapping("/nodes/root")
    public void getRootNodes(){
        System.out.println("GET /nodes/root");
    }
}
