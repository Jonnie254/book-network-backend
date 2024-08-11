package com.jonnie254.book_network_2.history;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookTransactionHistoryRepository extends JpaRepository<BookTransactionHistory, Integer> {

    @Query("""
            SELECT history from BookTransactionHistory history where history.user.id = :userId
            """)
    Page<BookTransactionHistory> findAllBorrowedBooks(Pageable pageable, Integer userId);

    @Query("""
            SELECT history from BookTransactionHistory history where history.book.owner.id = :userId
            """)
    Page<BookTransactionHistory> findAllReturnedBooks(Pageable pageable, Integer userId);

    @Query("""
            SELECT (COUNT(*) > 0) As isBorrowed from BookTransactionHistory history where history.user.id = :userId
            AND history.book.id = :bookId
            AND history.returnedApproved = false
            """)
    boolean isAlredyBorrowedByUser(Integer bookId, Integer userId);


    @Query("""
            SELECT history from BookTransactionHistory history where
            history.book.id = :bookId
            AND history.user.id = :userId
            AND history.returnedApproved = false
            AND history.returned = false
            """)
    Optional<BookTransactionHistory> findbyBookIdAndUserId(Integer bookId, Integer userId);

    @Query("""
            SELECT history from BookTransactionHistory history where
            history.book.id = :bookId
            AND history.book.owner.id = :userId
            AND history.returnedApproved = false
            AND history.returned = true
            """)
    Optional<BookTransactionHistory> findbyBookIdAndOwnerId(Integer bookId, Integer userId);
}
