;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname H02_Stollenwerk_Vincent) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f ())))
(require 2htdp/image)

;; Reference matrices: http://biecoll.ub.uni-bielefeld.de/volltexte/2007/52/pdf/ICVS2007-6.pdf
(define m-rgb-to-lms (list (list 17.8824 43.5161 4.1193) (list 3.4557 27.1554 3.8671) (list 0.02996 0.18431 1.4670)))
(define m-lms-to-lms-protanopia (list (list 0 2.02344 -2.52581) (list 0 1 0) (list 0 0 1)))
(define m-lms-to-rgb (list (list 0.0809 -0.1305 0.1167) (list -0.0102 0.0540 -0.1136) (list -0.0003 -0.0041 0.6935)))

;; H1
;; Type:
;; Returns:
;;(define (matrix-multiplication m1 m2)
  ;; insert code here
;;  )

;(check-expect (matrix-multiplication (list (list 4 3) (list 6 3)) (list (list 1 2 3) (list 4 5 6))) (list (list 16 23 30) (list 18 27 36)))
;(check-expect (matrix-multiplication (list (list 1 -5 8)  (list 1 -2 1)  (list 2 -1 -5)) (list (list 1 -5 8)  (list 1 -2 1)  (list 2 -1 -5))) (list (list 12 -3 -37) (list 1 -2 1) (list -9 -3 40)))

;; H1 helper function
;; Type: (list of (list of number)) -> (list of (list of number))
;; Precondition: All matrix rows must have the same amount of entries,
;;               the list should represent a matrix from H02
;;               (all inner lists must have the same amount of entries)
;; Returns: The columns of a matrix
(define (get-matrix-columns matrix)
  (if (empty? matrix)
      empty
      (if (empty? (first matrix))
          empty
          (cons (map first matrix) (get-matrix-columns (map rest matrix))))))

(check-expect (get-matrix-columns (list (list 1 2 5) (list 3 4 6) (list 8 9 10))) (list (list 1 3 8) (list 2 4 9) (list 5 6 10)))
(check-expect (get-matrix-columns empty) empty)
(check-expect (get-matrix-columns (list (list 1) (list 2) (list 3))) (list (list 1 2 3)))

;; H2
;; Type:
;; Returns:
;;(define (color->vector clr)
  ;; insert code here
;;)

;; Type:
;; Returns:
;;(define (vector->color vec)
  ;; insert code here
;;  )


;; H3
;; Type:
;; Returns:
;;(define (rgb-vector-protanopia vec)
  ;; insert code here
;;  )


;; H4
;; Type:
;; Returns:
;;(define (protanopia-recursive img)
  ;; insert code here
;;  )

;(define input (bitmap/file "rainbow_colors.png"))
;(define protanopia (protanopia-recursive input))
;(define solution (bitmap/file "solution_rainbow_colors.png"))
;(check-expect protanopia solution)
;(save-image protanopia "out_rainbow_colors.png")


;; H5
;; Type:
;; Returns:
;;(define (protanopia-map img)
  ;; insert code here
;;)


;; H6
;; Types:
;; Returns:
;;(define (image-similarity img1 img2 max-difference)
  ;; insert code here
;;  )
