#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

struct Node {
	char value;
	struct Node* ptr;
};

void push(struct Node** top, char m) {
	struct Node* k = malloc(sizeof(struct Node));
	k->value = m;
	k->ptr = *top;
	*top = k;
}

void display(struct Node* top)
{
	while(top->ptr != NULL) {
		printf("%c->", top->value);
		top = top->ptr;
	}
	printf("%c", top->value);
}

char pop(struct Node** top) {
	if (*top == NULL) {
		// fprintf(stderr, "Error: Stack underflow!\n");
		return '\0';
	}
	char c = (*top)->value;
	struct Node* temp = *top;
	*top = (*top)->ptr;
	free(temp);
	return c;
}

int preceed(char c) {
	if(c == '/' || c == '*') {
		return 2;
	} else if(c == '+' || c == '-') {
		return 1;
	}
	return 0;
}

int main() {
	struct Node* head = NULL;
	char s[25];
	char p[25];
	int i = 0;
	int j = 0;
	char x;
	scanf("%s", s);
	while(s[i] != '\0') {
		if(isalnum(s[i]) > 0) {
			p[j++] = s[i];
		} else if(s[i] == '(') {
			push(&head, s[i]);
		} else if(s[i] == ')') {
		    x = pop(&head);
			while(x != '('){
			     p[j++] = x;
			     x = pop(&head);
			}
		} else {
			 while(head != NULL && preceed(head->value) >= preceed(s[i])){
			      p[j++] = pop(&head);
			 }
			push(&head, s[i]);
		}
		i++;
	}
	while(head != NULL){
	    p[j++] = pop(&head);
	}
	printf("%s", p);
	return 0;
}
