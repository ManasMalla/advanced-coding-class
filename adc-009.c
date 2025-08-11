// Online C compiler to run C program online
#include <stdio.h>
#include <stdlib.h>

struct Node {
    int value;
    struct Node* ptr;
};

void push(struct Node** top, int m) {
    struct Node* k = malloc(sizeof(struct Node));
    k->value = m;
    k->ptr = *top;
    *top = k;
}

int main() {
    struct Node* head = NULL;
    int n;
    printf("Enter the number of elements: ");
    scanf("%d", &n);
    for (int i = 0; i < n; i++) {
        printf("Enter a number: ");
        int m;
        scanf("%d", &m);
        push(&head, m);
    }
    printf("The top element is: %d\n", head->value);
    return 0;
}