compile:
	@mkdir build || (echo 'Already compiled. Please run make clean first before compile'; exit 1;)
	@echo "Wait a second. Compiling.."
	@javac -g:none -nowarn -Xlint -classpath asm-all-4.1.jar: -d build bytecodeAST/*.java
	@echo "Done!"
run:
	@echo "Generating..."
	@java -cp asm-all-4.1.jar:build bytecodeAST.Main $(file)

.PHONY:clean
clean:
	@rm -r build || (echo "The build is already clean";  exit 1;)
	@echo "The build is now clean"
