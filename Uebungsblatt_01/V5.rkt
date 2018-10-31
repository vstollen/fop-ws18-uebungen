;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname V5) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
(define A (list (cons 1 empty) (list 7) 9))
(define B (cons 42 (list "Hello" "world" "!")))

(first (rest A))
(rest (first A))
(append (first B) (rest (rest A)) (first A))