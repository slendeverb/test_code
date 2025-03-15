#pragma once
#define _CRT_SECURE_NO_WARNINGS

#include<stdio.h>
#include<stdlib.h>
#include<time.h>

#define ROW 9
#define COL 9
#define ROWS ROW + 2
#define COLS COL + 2
#define COUNT 1

void init_board(char board[ROWS][COLS], int rows, int cols, char set);
void set_mine(char mine[ROWS][COLS], int row, int col);
void display_board(char board[ROWS][COLS], int row, int col);
void find_mind(char mine[ROWS][COLS], char show[ROWS][COLS], int row, int col);

