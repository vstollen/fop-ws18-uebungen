;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname V2_A) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
;; 1.
;; Auswertung:
;;   ((7 + 3) + 4) + (2 * 3)
;; = (10 + 4) + 6
;; = 14 + 6
;; = 20
( + ( + (+ 7 3 ) 4 ) ( * 2 3 ) )

;; 2.
;; Auswertung:
;;   (1 + 9) * (4 - (1 + 1))
;; = 10 * (4 - 2)
;; = 10 * 2
;; = 20
( * ( + 1 9 ) ( - 4 ( + 1 1 ) ) )

;; 3.
;; Auswertung:
;;   (0 - (2 * 3)) * ((3 / 3) - (5 - 6))
;; = (0 - 6) * (1 - (-1))
;; = -6 * (1 + 1)
;; = -6 * 2
;; = -12
( * ( - 0 ( * 2 3 ) ) ( - ( / 3 3 ) ( - 5 6 ) ) )

;; 4.
;; Auswertung:
;;   (5 * 5) + ((3 * 3) - ((4 * 4) - 4) - 4)
;; = 25 + (9 - (16 - 4) - 4)
;; = 25 + (9 - 12 - 4)
;; = 25 + (-7)
;; = 25 - 7
;; = 18
( + ( * 5 5 ) ( - ( - ( * 3 3 ) ( - ( * 4 4 ) 4 ) ) 4 ) )