;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname V6) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f ())))
(define-struct my-pair (one two))

(my-pair? (make-my-pair "a" "b"))
(make-my-pair 1 (make-my-pair 2 empty))
(* (my-pair-two (make-my-pair 1 2)) (my-pair-one (make-my-pair 3 4)))