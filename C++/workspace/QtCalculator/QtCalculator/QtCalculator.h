#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_QtCalculator.h"

class QtCalculator : public QMainWindow
{
    Q_OBJECT

public:
    QtCalculator(QWidget *parent = nullptr);
    ~QtCalculator();

private:
    Ui::QtCalculatorClass ui;
};
