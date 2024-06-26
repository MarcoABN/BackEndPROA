//CRUD Inicial validado

package com.ueg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ueg.exception.ResourceNotFoundException;
import com.ueg.model.NotaFiscal;
import com.ueg.repository.NotaFiscalRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/cnotafiscal/")
@RestController
public class NotaFiscalController {
    @Autowired
    private NotaFiscalRepository notaFiscalRep;

    //Metodo para listar notaFiscales
    @GetMapping("/notafiscal")
    public List<NotaFiscal> listar() {
        return this.notaFiscalRep.findAll();
    }

    //Metodo para consultar NotaFiscal
    @GetMapping("/notafiscal/{idNotaFiscal}")
    public ResponseEntity<NotaFiscal> consultar(@PathVariable Long idNotaFiscal) {
        NotaFiscal prest = this.notaFiscalRep.findById(idNotaFiscal).orElseThrow(() ->
            new ResourceNotFoundException("NotaFiscal não encontrado: " + idNotaFiscal));
        return ResponseEntity.ok(prest);
    }

    //Metodo para inserir
    @PostMapping("/notafiscal")
    public NotaFiscal inserir(@RequestBody NotaFiscal prest) {
        return this.notaFiscalRep.save(prest);
    }

    //Metodo para Alterar
    @PutMapping("/notafiscal/{idNotaFiscal}")
    public ResponseEntity<NotaFiscal> alterar(@PathVariable Long idNotaFiscal, @RequestBody NotaFiscal nf) {
        NotaFiscal nfLocalizado = this.notaFiscalRep.findById(idNotaFiscal).orElseThrow(() ->
            new ResourceNotFoundException("NotaFiscal não encontrada: " + idNotaFiscal));

        nfLocalizado.setID(nf.getID());
        nfLocalizado.setCNPJVendedor(nf.getCNPJVendedor());
        nfLocalizado.setRazaoSocial(nf.getRazaoSocial());
        nfLocalizado.setDtVenda(nf.getDtVenda());
        nfLocalizado.setLocal(nf.getLocal());
        nfLocalizado.setNumeroNotaFiscal(nf.getNumeroNotaFiscal());

        //Relacionamento
        nfLocalizado.setEmbarcacao(nf.getEmbarcacao());

        NotaFiscal atualizado = this.notaFiscalRep.save(nfLocalizado);
        return ResponseEntity.ok(atualizado);
    }

    //Metodo para Deletar
    @DeleteMapping("/notafiscal/{idNotaFiscal}")
    public ResponseEntity<Map<String, Boolean>> excluir(@PathVariable Long idNotaFiscal) {
        NotaFiscal nfLocalizado = this.notaFiscalRep.findById(idNotaFiscal).orElseThrow(() ->
            new ResourceNotFoundException("NotaFiscal não encontrado: " + idNotaFiscal));

        this.notaFiscalRep.delete(nfLocalizado);

        Map<String, Boolean> resposta = new HashMap<>();
        resposta.put("NotaFiscal Excluida!", Boolean.TRUE);
        return ResponseEntity.ok(resposta);
    }
}
