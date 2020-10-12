package com.projetozup.clientdatabase;

import com.projetozup.clientdatabase.controller.CadastroClienteController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(CadastroClienteController.class)
public class CadastroClienteTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CadastroClienteController cadastroClienteController;

    @Test
    public void testeDaApiQueCadastraUmNovoClienteNoBanco() {

    }
}
