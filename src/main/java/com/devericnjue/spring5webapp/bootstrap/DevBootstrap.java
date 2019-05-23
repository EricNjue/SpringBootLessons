package com.devericnjue.spring5webapp.bootstrap;

import com.devericnjue.spring5webapp.model.Author;
import com.devericnjue.spring5webapp.model.Book;
import com.devericnjue.spring5webapp.model.Publisher;
import com.devericnjue.spring5webapp.repositories.AuthorRepository;
import com.devericnjue.spring5webapp.repositories.BookRepository;
import com.devericnjue.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository,
                        PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        Publisher dddPublisher = new Publisher("Bible Society of Kenya", "1435 - 00200, Nrb");
        Publisher noEJBPublisher = new Publisher("Kenya Institute of Education", "00200- 1477, Msa");
        publisherRepository.save(dddPublisher);
        publisherRepository.save(noEJBPublisher);

        //Eric
        Author eric = new Author("Eric", "Njue");

        Book ddd = new Book("Domain Driven Design", "1234", dddPublisher);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        // Rod
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "23444", noEJBPublisher);
        rod.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

    }
}
