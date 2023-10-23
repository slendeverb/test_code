/********************************************************************************
** Form generated from reading UI file 'QtCalculator.ui'
**
** Created by: Qt User Interface Compiler version 5.14.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_QTCALCULATOR_H
#define UI_QTCALCULATOR_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_QtCalculatorClass
{
public:
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QWidget *centralWidget;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *QtCalculatorClass)
    {
        if (QtCalculatorClass->objectName().isEmpty())
            QtCalculatorClass->setObjectName(QString::fromUtf8("QtCalculatorClass"));
        QtCalculatorClass->resize(600, 400);
        menuBar = new QMenuBar(QtCalculatorClass);
        menuBar->setObjectName(QString::fromUtf8("menuBar"));
        QtCalculatorClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(QtCalculatorClass);
        mainToolBar->setObjectName(QString::fromUtf8("mainToolBar"));
        QtCalculatorClass->addToolBar(mainToolBar);
        centralWidget = new QWidget(QtCalculatorClass);
        centralWidget->setObjectName(QString::fromUtf8("centralWidget"));
        QtCalculatorClass->setCentralWidget(centralWidget);
        statusBar = new QStatusBar(QtCalculatorClass);
        statusBar->setObjectName(QString::fromUtf8("statusBar"));
        QtCalculatorClass->setStatusBar(statusBar);

        retranslateUi(QtCalculatorClass);

        QMetaObject::connectSlotsByName(QtCalculatorClass);
    } // setupUi

    void retranslateUi(QMainWindow *QtCalculatorClass)
    {
        QtCalculatorClass->setWindowTitle(QCoreApplication::translate("QtCalculatorClass", "QtCalculator", nullptr));
    } // retranslateUi

};

namespace Ui {
    class QtCalculatorClass: public Ui_QtCalculatorClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_QTCALCULATOR_H
