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
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_QtCalculatorClass
{
public:
    QWidget *centralWidget;
    QWidget *layoutWidget;
    QGridLayout *gridLayout;
    QPushButton *pushButton;
    QPushButton *pushButton_12;
    QPushButton *pushButton_24;
    QPushButton *btn_del;
    QPushButton *pushButton_2;
    QPushButton *pushButton_8;
    QPushButton *pushButton_14;
    QPushButton *btn_div;
    QPushButton *btn_num7;
    QPushButton *btn_num8;
    QPushButton *btn_num9;
    QPushButton *btn_mul;
    QPushButton *btn_num4;
    QPushButton *btn_num5;
    QPushButton *btn_num6;
    QPushButton *btn_sub;
    QPushButton *btn_num1;
    QPushButton *btn_num2;
    QPushButton *btn_num3;
    QPushButton *btn_add;
    QPushButton *btn_numAddOrSub;
    QPushButton *btn_num0;
    QPushButton *btn_numDot;
    QPushButton *btn_equal;
    QWidget *layoutWidget1;
    QVBoxLayout *verticalLayout;
    QLineEdit *lineEdit_2;
    QLineEdit *lineEdit;
    QToolBar *mainToolBar;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *QtCalculatorClass)
    {
        if (QtCalculatorClass->objectName().isEmpty())
            QtCalculatorClass->setObjectName(QString::fromUtf8("QtCalculatorClass"));
        QtCalculatorClass->resize(461, 558);
        QFont font;
        font.setPointSize(20);
        QtCalculatorClass->setFont(font);
        QtCalculatorClass->setStyleSheet(QString::fromUtf8("*\n"
"{\n"
"	border:none;\n"
"	background-color:rgb(239,244,249);\n"
"}\n"
"\n"
"QPushButton\n"
"{\n"
"	font:normal 9pt '\345\276\256\350\275\257\351\233\205\351\273\221';\n"
"	background-color:rgb(247,249,252);\n"
"}\n"
"\n"
"QPushButton:hover\n"
"{\n"
"	border:1px solid rgb(193,193,193);\n"
"	background-color:rgb(244,246,250);\n"
"}\n"
"\n"
"QPushButton#btn_num0,#btn_num1,#btn_num2,#btn_num3,#btn_num4,#btn_num5,#btn_num6,#btn_num7,#btn_num8,#btn_num9,#btn_numAddOrSub,#btn_numDot\n"
"{\n"
"	font:normal 12pt '\345\276\256\350\275\257\351\233\205\351\273\221';\n"
"	background-color:rgb(255,255,255);\n"
"}\n"
"\n"
"QPushButton#btn_num0:hover,#btn_num1:hover,#btn_num2:hover,#btn_num3:hover,#btn_num4:hover,#btn_num5:hover,#btn_num6:hover,#btn_num7:hover,#btn_num8:hover,#btn_num9:hover,#btn_numAddOrSub:hover,#btn_numDot:hover\n"
"{\n"
"	border:1px solid rgb(193,193,193);\n"
"	background-color:rgb(251,252,253);\n"
"}"));
        centralWidget = new QWidget(QtCalculatorClass);
        centralWidget->setObjectName(QString::fromUtf8("centralWidget"));
        layoutWidget = new QWidget(centralWidget);
        layoutWidget->setObjectName(QString::fromUtf8("layoutWidget"));
        layoutWidget->setGeometry(QRect(0, 140, 461, 381));
        gridLayout = new QGridLayout(layoutWidget);
        gridLayout->setSpacing(2);
        gridLayout->setContentsMargins(11, 11, 11, 11);
        gridLayout->setObjectName(QString::fromUtf8("gridLayout"));
        gridLayout->setContentsMargins(0, 0, 0, 0);
        pushButton = new QPushButton(layoutWidget);
        pushButton->setObjectName(QString::fromUtf8("pushButton"));
        QSizePolicy sizePolicy(QSizePolicy::Preferred, QSizePolicy::Preferred);
        sizePolicy.setHorizontalStretch(0);
        sizePolicy.setVerticalStretch(0);
        sizePolicy.setHeightForWidth(pushButton->sizePolicy().hasHeightForWidth());
        pushButton->setSizePolicy(sizePolicy);

        gridLayout->addWidget(pushButton, 0, 0, 1, 1);

        pushButton_12 = new QPushButton(layoutWidget);
        pushButton_12->setObjectName(QString::fromUtf8("pushButton_12"));
        sizePolicy.setHeightForWidth(pushButton_12->sizePolicy().hasHeightForWidth());
        pushButton_12->setSizePolicy(sizePolicy);

        gridLayout->addWidget(pushButton_12, 0, 1, 1, 1);

        pushButton_24 = new QPushButton(layoutWidget);
        pushButton_24->setObjectName(QString::fromUtf8("pushButton_24"));
        sizePolicy.setHeightForWidth(pushButton_24->sizePolicy().hasHeightForWidth());
        pushButton_24->setSizePolicy(sizePolicy);

        gridLayout->addWidget(pushButton_24, 0, 2, 1, 1);

        btn_del = new QPushButton(layoutWidget);
        btn_del->setObjectName(QString::fromUtf8("btn_del"));
        sizePolicy.setHeightForWidth(btn_del->sizePolicy().hasHeightForWidth());
        btn_del->setSizePolicy(sizePolicy);

        gridLayout->addWidget(btn_del, 0, 3, 1, 1);

        pushButton_2 = new QPushButton(layoutWidget);
        pushButton_2->setObjectName(QString::fromUtf8("pushButton_2"));
        sizePolicy.setHeightForWidth(pushButton_2->sizePolicy().hasHeightForWidth());
        pushButton_2->setSizePolicy(sizePolicy);
        pushButton_2->setAutoDefault(false);

        gridLayout->addWidget(pushButton_2, 1, 0, 1, 1);

        pushButton_8 = new QPushButton(layoutWidget);
        pushButton_8->setObjectName(QString::fromUtf8("pushButton_8"));
        sizePolicy.setHeightForWidth(pushButton_8->sizePolicy().hasHeightForWidth());
        pushButton_8->setSizePolicy(sizePolicy);
        pushButton_8->setAutoDefault(false);

        gridLayout->addWidget(pushButton_8, 1, 1, 1, 1);

        pushButton_14 = new QPushButton(layoutWidget);
        pushButton_14->setObjectName(QString::fromUtf8("pushButton_14"));
        sizePolicy.setHeightForWidth(pushButton_14->sizePolicy().hasHeightForWidth());
        pushButton_14->setSizePolicy(sizePolicy);
        pushButton_14->setAutoDefault(false);

        gridLayout->addWidget(pushButton_14, 1, 2, 1, 1);

        btn_div = new QPushButton(layoutWidget);
        btn_div->setObjectName(QString::fromUtf8("btn_div"));
        sizePolicy.setHeightForWidth(btn_div->sizePolicy().hasHeightForWidth());
        btn_div->setSizePolicy(sizePolicy);

        gridLayout->addWidget(btn_div, 1, 3, 1, 1);

        btn_num7 = new QPushButton(layoutWidget);
        btn_num7->setObjectName(QString::fromUtf8("btn_num7"));
        sizePolicy.setHeightForWidth(btn_num7->sizePolicy().hasHeightForWidth());
        btn_num7->setSizePolicy(sizePolicy);
        btn_num7->setAutoDefault(false);

        gridLayout->addWidget(btn_num7, 2, 0, 1, 1);

        btn_num8 = new QPushButton(layoutWidget);
        btn_num8->setObjectName(QString::fromUtf8("btn_num8"));
        sizePolicy.setHeightForWidth(btn_num8->sizePolicy().hasHeightForWidth());
        btn_num8->setSizePolicy(sizePolicy);
        btn_num8->setAutoDefault(false);

        gridLayout->addWidget(btn_num8, 2, 1, 1, 1);

        btn_num9 = new QPushButton(layoutWidget);
        btn_num9->setObjectName(QString::fromUtf8("btn_num9"));
        sizePolicy.setHeightForWidth(btn_num9->sizePolicy().hasHeightForWidth());
        btn_num9->setSizePolicy(sizePolicy);
        btn_num9->setAutoDefault(false);

        gridLayout->addWidget(btn_num9, 2, 2, 1, 1);

        btn_mul = new QPushButton(layoutWidget);
        btn_mul->setObjectName(QString::fromUtf8("btn_mul"));
        sizePolicy.setHeightForWidth(btn_mul->sizePolicy().hasHeightForWidth());
        btn_mul->setSizePolicy(sizePolicy);

        gridLayout->addWidget(btn_mul, 2, 3, 1, 1);

        btn_num4 = new QPushButton(layoutWidget);
        btn_num4->setObjectName(QString::fromUtf8("btn_num4"));
        sizePolicy.setHeightForWidth(btn_num4->sizePolicy().hasHeightForWidth());
        btn_num4->setSizePolicy(sizePolicy);
        btn_num4->setAutoDefault(false);

        gridLayout->addWidget(btn_num4, 3, 0, 1, 1);

        btn_num5 = new QPushButton(layoutWidget);
        btn_num5->setObjectName(QString::fromUtf8("btn_num5"));
        sizePolicy.setHeightForWidth(btn_num5->sizePolicy().hasHeightForWidth());
        btn_num5->setSizePolicy(sizePolicy);
        btn_num5->setAutoDefault(false);

        gridLayout->addWidget(btn_num5, 3, 1, 1, 1);

        btn_num6 = new QPushButton(layoutWidget);
        btn_num6->setObjectName(QString::fromUtf8("btn_num6"));
        sizePolicy.setHeightForWidth(btn_num6->sizePolicy().hasHeightForWidth());
        btn_num6->setSizePolicy(sizePolicy);
        btn_num6->setAutoDefault(false);

        gridLayout->addWidget(btn_num6, 3, 2, 1, 1);

        btn_sub = new QPushButton(layoutWidget);
        btn_sub->setObjectName(QString::fromUtf8("btn_sub"));
        sizePolicy.setHeightForWidth(btn_sub->sizePolicy().hasHeightForWidth());
        btn_sub->setSizePolicy(sizePolicy);

        gridLayout->addWidget(btn_sub, 3, 3, 1, 1);

        btn_num1 = new QPushButton(layoutWidget);
        btn_num1->setObjectName(QString::fromUtf8("btn_num1"));
        sizePolicy.setHeightForWidth(btn_num1->sizePolicy().hasHeightForWidth());
        btn_num1->setSizePolicy(sizePolicy);
        btn_num1->setAutoDefault(false);

        gridLayout->addWidget(btn_num1, 4, 0, 1, 1);

        btn_num2 = new QPushButton(layoutWidget);
        btn_num2->setObjectName(QString::fromUtf8("btn_num2"));
        sizePolicy.setHeightForWidth(btn_num2->sizePolicy().hasHeightForWidth());
        btn_num2->setSizePolicy(sizePolicy);
        btn_num2->setAutoDefault(false);

        gridLayout->addWidget(btn_num2, 4, 1, 1, 1);

        btn_num3 = new QPushButton(layoutWidget);
        btn_num3->setObjectName(QString::fromUtf8("btn_num3"));
        sizePolicy.setHeightForWidth(btn_num3->sizePolicy().hasHeightForWidth());
        btn_num3->setSizePolicy(sizePolicy);
        btn_num3->setAutoDefault(false);

        gridLayout->addWidget(btn_num3, 4, 2, 1, 1);

        btn_add = new QPushButton(layoutWidget);
        btn_add->setObjectName(QString::fromUtf8("btn_add"));
        sizePolicy.setHeightForWidth(btn_add->sizePolicy().hasHeightForWidth());
        btn_add->setSizePolicy(sizePolicy);

        gridLayout->addWidget(btn_add, 4, 3, 1, 1);

        btn_numAddOrSub = new QPushButton(layoutWidget);
        btn_numAddOrSub->setObjectName(QString::fromUtf8("btn_numAddOrSub"));
        sizePolicy.setHeightForWidth(btn_numAddOrSub->sizePolicy().hasHeightForWidth());
        btn_numAddOrSub->setSizePolicy(sizePolicy);
        btn_numAddOrSub->setAutoDefault(false);

        gridLayout->addWidget(btn_numAddOrSub, 5, 0, 1, 1);

        btn_num0 = new QPushButton(layoutWidget);
        btn_num0->setObjectName(QString::fromUtf8("btn_num0"));
        sizePolicy.setHeightForWidth(btn_num0->sizePolicy().hasHeightForWidth());
        btn_num0->setSizePolicy(sizePolicy);
        btn_num0->setAutoDefault(false);

        gridLayout->addWidget(btn_num0, 5, 1, 1, 1);

        btn_numDot = new QPushButton(layoutWidget);
        btn_numDot->setObjectName(QString::fromUtf8("btn_numDot"));
        sizePolicy.setHeightForWidth(btn_numDot->sizePolicy().hasHeightForWidth());
        btn_numDot->setSizePolicy(sizePolicy);
        btn_numDot->setAutoDefault(false);

        gridLayout->addWidget(btn_numDot, 5, 2, 1, 1);

        btn_equal = new QPushButton(layoutWidget);
        btn_equal->setObjectName(QString::fromUtf8("btn_equal"));
        sizePolicy.setHeightForWidth(btn_equal->sizePolicy().hasHeightForWidth());
        btn_equal->setSizePolicy(sizePolicy);

        gridLayout->addWidget(btn_equal, 5, 3, 1, 1);

        layoutWidget1 = new QWidget(centralWidget);
        layoutWidget1->setObjectName(QString::fromUtf8("layoutWidget1"));
        layoutWidget1->setGeometry(QRect(0, 0, 461, 141));
        verticalLayout = new QVBoxLayout(layoutWidget1);
        verticalLayout->setSpacing(2);
        verticalLayout->setContentsMargins(11, 11, 11, 11);
        verticalLayout->setObjectName(QString::fromUtf8("verticalLayout"));
        verticalLayout->setContentsMargins(0, 0, 0, 0);
        lineEdit_2 = new QLineEdit(layoutWidget1);
        lineEdit_2->setObjectName(QString::fromUtf8("lineEdit_2"));
        sizePolicy.setHeightForWidth(lineEdit_2->sizePolicy().hasHeightForWidth());
        lineEdit_2->setSizePolicy(sizePolicy);
        QFont font1;
        font1.setPointSize(10);
        lineEdit_2->setFont(font1);
        lineEdit_2->setAlignment(Qt::AlignRight|Qt::AlignTrailing|Qt::AlignVCenter);
        lineEdit_2->setReadOnly(true);

        verticalLayout->addWidget(lineEdit_2);

        lineEdit = new QLineEdit(layoutWidget1);
        lineEdit->setObjectName(QString::fromUtf8("lineEdit"));
        sizePolicy.setHeightForWidth(lineEdit->sizePolicy().hasHeightForWidth());
        lineEdit->setSizePolicy(sizePolicy);
        QFont font2;
        font2.setPointSize(26);
        lineEdit->setFont(font2);
        lineEdit->setAlignment(Qt::AlignRight|Qt::AlignTrailing|Qt::AlignVCenter);
        lineEdit->setReadOnly(true);

        verticalLayout->addWidget(lineEdit);

        QtCalculatorClass->setCentralWidget(centralWidget);
        mainToolBar = new QToolBar(QtCalculatorClass);
        mainToolBar->setObjectName(QString::fromUtf8("mainToolBar"));
        QtCalculatorClass->addToolBar(Qt::TopToolBarArea, mainToolBar);
        statusBar = new QStatusBar(QtCalculatorClass);
        statusBar->setObjectName(QString::fromUtf8("statusBar"));
        QtCalculatorClass->setStatusBar(statusBar);

        retranslateUi(QtCalculatorClass);

        QMetaObject::connectSlotsByName(QtCalculatorClass);
    } // setupUi

    void retranslateUi(QMainWindow *QtCalculatorClass)
    {
        QtCalculatorClass->setWindowTitle(QCoreApplication::translate("QtCalculatorClass", "QtCalculator", nullptr));
        pushButton->setText(QCoreApplication::translate("QtCalculatorClass", "%", nullptr));
        pushButton_12->setText(QCoreApplication::translate("QtCalculatorClass", "CE", nullptr));
        pushButton_24->setText(QCoreApplication::translate("QtCalculatorClass", "C", nullptr));
        btn_del->setText(QCoreApplication::translate("QtCalculatorClass", "del", nullptr));
        pushButton_2->setText(QCoreApplication::translate("QtCalculatorClass", "1/x", nullptr));
        pushButton_8->setText(QCoreApplication::translate("QtCalculatorClass", "x\302\262", nullptr));
        pushButton_14->setText(QCoreApplication::translate("QtCalculatorClass", "\302\262\342\210\232x", nullptr));
        btn_div->setText(QCoreApplication::translate("QtCalculatorClass", "/", nullptr));
        btn_num7->setText(QCoreApplication::translate("QtCalculatorClass", "7", nullptr));
        btn_num8->setText(QCoreApplication::translate("QtCalculatorClass", "8", nullptr));
        btn_num9->setText(QCoreApplication::translate("QtCalculatorClass", "9", nullptr));
        btn_mul->setText(QCoreApplication::translate("QtCalculatorClass", "x", nullptr));
        btn_num4->setText(QCoreApplication::translate("QtCalculatorClass", "4", nullptr));
        btn_num5->setText(QCoreApplication::translate("QtCalculatorClass", "5", nullptr));
        btn_num6->setText(QCoreApplication::translate("QtCalculatorClass", "6", nullptr));
        btn_sub->setText(QCoreApplication::translate("QtCalculatorClass", "-", nullptr));
        btn_num1->setText(QCoreApplication::translate("QtCalculatorClass", "1", nullptr));
        btn_num2->setText(QCoreApplication::translate("QtCalculatorClass", "2", nullptr));
        btn_num3->setText(QCoreApplication::translate("QtCalculatorClass", "3", nullptr));
        btn_add->setText(QCoreApplication::translate("QtCalculatorClass", "+", nullptr));
        btn_numAddOrSub->setText(QCoreApplication::translate("QtCalculatorClass", "+/-", nullptr));
        btn_num0->setText(QCoreApplication::translate("QtCalculatorClass", "0", nullptr));
        btn_numDot->setText(QCoreApplication::translate("QtCalculatorClass", ".", nullptr));
        btn_equal->setText(QCoreApplication::translate("QtCalculatorClass", "=", nullptr));
        lineEdit_2->setText(QString());
        lineEdit->setText(QCoreApplication::translate("QtCalculatorClass", "0", nullptr));
    } // retranslateUi

};

namespace Ui {
    class QtCalculatorClass: public Ui_QtCalculatorClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_QTCALCULATOR_H
