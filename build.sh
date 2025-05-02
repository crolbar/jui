#!/usr/bin/env bash
set -e

clear=0
run=0

for arg in "$@"; do
    if [[ "$arg" == "clear" ]]; then
        clear=1
    fi
    if [[ "$arg" == "run" ]]; then
        run=1
    fi
done

if [ $clear -eq 1 ]; then
    rm -rf out
fi

SRC_DIR=src
OUT_DIR=out

sources=$(find "$SRC_DIR" -name '*.java')

if [ -z "$sources" ]; then
    echo "No Java source files found."
    exit 1
fi

mkdir -p $OUT_DIR


javac -Xlint -d $OUT_DIR $sources


if [ $run -eq 1 ]; then
    java -cp out main.Main
fi
