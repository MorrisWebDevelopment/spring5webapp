package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher publisher = new Publisher();
        publisher.setName("Dog Publishing");
        publisher.setCity("Any Town USA");
        publisher.setState("MO");
        publisherRepository.save(publisher);

        Author Eric = new Author("Eric", "Evans");
        Book DarkStains = new Book("Dark Stains", "w12345");
        Eric.getBooks().add(DarkStains);
        DarkStains.getAuthors().add(Eric);
        DarkStains.setPublisher(publisher);
        publisher.getBooks().add(DarkStains);

        authorRepository.save(Eric);
        bookRepository.save(DarkStains);
        publisherRepository.save(publisher);


        Author david = new Author("David", "Phillips");
        Book mock = new Book("Mocking Bird", "m12345");
        david.getBooks().add(mock);
        mock.getAuthors().add(david);

        authorRepository.save(david);
        bookRepository.save(mock);




        System.out.println("Starting Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of Authors: " + authorRepository.count());
        System.out.println("Number of Publishers " + publisher.getBooks().size());

    }
}
