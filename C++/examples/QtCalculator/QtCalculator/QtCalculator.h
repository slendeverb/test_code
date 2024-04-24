#pragma once

#include <QtWidgets/QMainWindow>
#include <QtWidgets/QWidget>
#include <QtWidgets/QButtonGroup>
#include <QDebug>
#include "ui_QtCalculator.h"

class QtCalculator : public QMainWindow
{
    Q_OBJECT

public:
    QtCalculator(QWidget *parent = nullptr);
    ~QtCalculator();
    void initUI();

public slots:
    void onButtonGroupClicked(QAbstractButton* btn);

private:
    Ui::QtCalculatorClass* ui;
    QButtonGroup* buttonGroup;
    QVector<QVariant> vec;
};
