;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname V2) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
;; Type: (natural -> number) -> number
;; Returns: The return of the given function.
(define (apply-as-double fct) fct)

(define z (apply-as-double ((lambda (x) (* x 10)) 11)))

z