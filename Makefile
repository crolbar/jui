.PHONY: run clean

SRC_DIR=src
OUT_DIR=out
SOURCES=$(shell find $(SRC_DIR) -name '*.java')

$(OUT_DIR): $(SOURCES)
	javac -Xlint -d $(OUT_DIR) $(SOURCES)

run: $(OUT_DIR)
	java -cp $(OUT_DIR) main.Main

clean:
	$(RM) -r $(OUT_DIR)
