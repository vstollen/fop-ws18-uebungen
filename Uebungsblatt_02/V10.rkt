;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname V10) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f ())))
(require 2htdp/image)

(define black (make-color 0 0 0 255))
(define white (make-color 255 255 255 255))

;; Type: image -> (list of number number)
;; Returns: List with the count of black pixels at first position
;;          and the number of white pixels at second position
(define (count-black-white img)
  ;; Type: color (list of number number) -> (list of number number)
  ;; Returns: Given list, with 1 added to first position if pixel is black
  ;;          and 1 added to second position if pixel is white
  (foldr (lambda (pixel-color counter-list)
                (list (if (equal? pixel-color black)
                          (+ (first counter-list) 1)
                          (first counter-list))
                      (if (equal? pixel-color white)
                          (+ (first (rest counter-list)) 1)
                          (first (rest counter-list)))))
         (list 0 0)
         (image->color-list img)))

(check-expect (count-black-white (color-list->bitmap (list black black white black) 2 2)) (list 3 1))
(check-expect (count-black-white (color-list->bitmap empty 0 0)) (list 0 0))
(check-expect (count-black-white (color-list->bitmap (list white white white black black) 1 5)) (list 2 3))

;; Type: image -> image
;; Returns: Negative of the image
(define (negative-transformation img)
  (color-list->bitmap
   ;; Type: color -> color
   ;; Returns: Negative of a color
   (map (lambda (pixel-color)
         (make-color (- 255 (color-red pixel-color)) (- 255 (color-green pixel-color)) (- 255 (color-blue pixel-color)) (color-alpha pixel-color)))
         (image->color-list img))
   (image-width img)
   (image-height img)))

(check-expect (negative-transformation (color-list->bitmap (list black white black white) 2 2)) (color-list->bitmap (list white black white black) 2 2))
(check-expect (negative-transformation (color-list->bitmap (list (make-color 100 200 255 255) (make-color 20 45 27 100)) 1 2)) (color-list->bitmap (list (make-color 155 55 0 255) (make-color 235 210 228 100)) 1 2))
(check-expect (negative-transformation (color-list->bitmap empty 0 0)) (color-list->bitmap empty 0 0))