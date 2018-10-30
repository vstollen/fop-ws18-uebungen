;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname V3) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
;; Type: number number number number -> number
;; Returns: The distance between two two dimensional points
(define (circles-distance x1 y1 x2 y2)
  (sqrt (+ (* (- x2 x1) (- x2 x1)) (* (- y2 y1) (- y2 y1)))))

(check-expect (circles-distance 4 5 4 5) 0)
(check-expect (circles-distance 0 0 3 4) 5)
(check-expect (circles-distance 5 6 25 27) 29)

;; Type: number number number number number number -> String
;; Returns: Description of the relative position of two circles
(define (circles-position x1 y1 r1 x2 y2 r2)
  (if (< (circles-distance x1 y1 x2 y2) (abs (- r1 r2)))
      "Interior"
      (if (< (+ r1 r2) (circles-distance x1 y1 x2 y2))
          "External"
          "Intersect")))

(check-expect (circles-position -4 0 5 -5 3 1) "Interior")
(check-expect (circles-position -5 3 1 0 0 2) "External")
(check-expect (circles-position 0 0 2 -4 0 5) "Intersect")