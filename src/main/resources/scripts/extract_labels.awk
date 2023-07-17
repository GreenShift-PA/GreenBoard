#!/usr/bin/awk -f

BEGIN {
    FS="[<>\"=]"   # Set the field separator to "<", ">", "\"", and "="
    inText = 0
}

/text/ {
    inText = 1
    text = ""
}

/<\// {
    inText = 0
    if (text != "") {
        print text
    }
}

inText && /text/ {
    text = $3
}

