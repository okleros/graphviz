all:
	javac src/view/Janela.java
	java src/view/Janela

clean:
	del /s /q *.class
