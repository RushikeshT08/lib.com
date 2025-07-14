package com.capgemini.project.services;


import com.capgemini.project.entities.Author;
import com.capgemini.project.repositories.AuthorRepository;
import com.capgemini.project.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Long id, Author author) {
        Author existingAuthor = authorRepository.findById(id).orElse(null);
        if (existingAuthor != null) {
            existingAuthor.setAuthorId(author.getAuthorId());
            existingAuthor.setName(author.getName());
            existingAuthor.setBio(author.getBio());
            return authorRepository.save(existingAuthor);
        }
        return null;
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
    

    @Override
    public boolean authorExists(Long authorId) {
        return authorRepository.existsById(authorId);
    }
}

