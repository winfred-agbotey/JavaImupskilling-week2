package com.example.topdown.controller;



import com.example.topdown.model.Book;
import com.example.topdown.service.BookService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void testSearchBooks() throws Exception {
        List<Book> books = List.of(new Book("new book", "josh", "66544"));
        when(bookService.searchBooks(anyString())).thenReturn(books);

        mockMvc.perform(MockMvcRequestBuilders.get("/search").param("query", "hello"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("books"))
                .andExpect(MockMvcResultMatchers.model().attribute("books", books))
                .andExpect(MockMvcResultMatchers.model().attribute("books", Matchers.hasItem(Matchers.allOf(
                        Matchers.hasProperty("title",Matchers.is("new book")),
                        Matchers.hasProperty("author",Matchers.is("josh"))
                ))));
    }

}