# 422Checkstyle

Structural metrics:
Category A:
 Halstead Length is the sum of the total number of operators and operand [1,2] - operators + operands
 Halstead Vocabulary is the sum of the number of unique operators and unique - unique operators + unique operands
operands [1,2]
 Halstead Volume is the program length (N) times the log2 of the program vocabulary (n) 
[1,2] : Volume = N log2 n 
 Halstead Difficulty is half of the unique operators multiplied by the total number of 
operands, divided by the number of unique operands [1,2]
 Halstead Effort is the difficulty multiplied by the volume. Effort = DV. Effort was 
intended as a suggestion for how long code review might take [1,2]
Category B:
 Number of comments [3] - Lines in a block comment are equal to the line number of the closing comment node minus the line number of the opening block comment+1
 Number of lines of comments [3]
 Number of looping statements [3]
 Number of operators [3] - add it to a set of encountered operators
 Number of operands [3] - counting arguments of counted operators
 Number of expressions [3]
Optional/Extra credits:
 Number of variable declarations [4] -  whenever variable def token
 External Method references are the number of methods called from an external class [4]
 Local method references are the number of methods called from the same class [4]
 Number of casts - whenever a cast token found ++
 Maintainability Index measures how maintainable the source code is. 
The maintainability index is calculated as a factored formula consisting of Lines of Code, 
Cyclomatic Complexity and Halstead volume [5]: 
MI  = 171 - 5.2 * log2(V) - 0.23 * G - 16.2 * log2 (LOC) + 50 * sin (sqrt(2.4 * CM)), where:
 V = Halstead Volume
 G = Cyclomatic Complexity
 LOC = count of source Lines Of Code (SLOC)
 CM = percent of lines of Comment (optional)
