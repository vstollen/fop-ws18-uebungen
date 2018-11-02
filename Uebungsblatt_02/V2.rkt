;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname V2) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
(map + (list 1 2 3) (list 4 5 6))
(filter positive? (list 1 -2 3 4 -5))
(foldr + 0 (list 5 -9 3 2 5 6))
(filter string? (list 1 2 "3" 4 "abc"))
(first (map list (list "x" "y" "z")))
(map list (list "a" "b" "c") (list 1 2 3) (list true false true))
(foldr cons (list -10 -1) (list 1 10 100 1000))
(foldr list (list -10 -1) (list 1 10 100 1000))