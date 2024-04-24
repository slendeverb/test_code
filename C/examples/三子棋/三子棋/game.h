#pragma once
#define _CRT_SECURE_NO_WARNINGS
#define ROW 3
#define COL 3

#include<stdio.h>
#include<stdlib.h>
#include<time.h>
#include<windows.h>

void InitBoard(char board[ROW][COL],int row,int col);

void DisplayInitBoard(char board[ROW][COL],int row,int col);

void PlayerMove(char board[ROW][COL], int row, int col);

void ComputerMove(char board[ROW][COL], int row, int col);

char Is_Win(char board[ROW][COL], int row, int col);

