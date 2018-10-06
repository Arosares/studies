FILES = $(wildcard **/*.tex)

seminar.pdf: $(FILES)
	latexmk -interaction=nonstopmode -pdf seminar.tex

clean:
	latexmk -c