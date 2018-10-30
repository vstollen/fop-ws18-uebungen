;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname V4) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
;; Type: natural natural -> natural
;; Returns: Greatest common divisor given two numbers
(define (euclid a b)
  (if (= b 0)
      a
      (if (= a 0)
          b
          (if (> a b)
              (euclid (- a b) b)
              (euclid a (- b a))))))

(check-expect (euclid 3528 3780) 252)
(check-expect (euclid 5 7) 1)
(check-expect (euclid 264 0) 264)
