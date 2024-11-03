/********************************************************************************
** Form generated from reading UI file 'DemoQt.ui'
**
** Created by: Qt User Interface Compiler version 5.14.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_DEMOQT_H
#define UI_DEMOQT_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QSpacerItem>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_DemoQtClass
{
public:
    QLabel *clientLabel;
    QLabel *ipLabel;
    QLineEdit *ipLineEdit;
    QLabel *portLabel;
    QLineEdit *portLineEdit;
    QWidget *horizontalLayoutWidget;
    QHBoxLayout *horizontalLayout;
    QPushButton *connectPushButton;
    QSpacerItem *horizontalSpacer;
    QPushButton *cancelPushButton;

    void setupUi(QWidget *DemoQtClass)
    {
        if (DemoQtClass->objectName().isEmpty())
            DemoQtClass->setObjectName(QString::fromUtf8("DemoQtClass"));
        DemoQtClass->resize(766, 561);
        DemoQtClass->setStyleSheet(QString::fromUtf8("QLineEdit{\n"
"border:1px solid #6D7175;\n"
"border-radius:2px;\n"
"background-color:white;\n"
"height:20px;\n"
"font-size:20px;\n"
"color:#000000;\n"
"}"));
        clientLabel = new QLabel(DemoQtClass);
        clientLabel->setObjectName(QString::fromUtf8("clientLabel"));
        clientLabel->setGeometry(QRect(310, 70, 121, 41));
        QFont font;
        font.setFamily(QString::fromUtf8("\345\276\256\350\275\257\351\233\205\351\273\221"));
        font.setPointSize(20);
        clientLabel->setFont(font);
        ipLabel = new QLabel(DemoQtClass);
        ipLabel->setObjectName(QString::fromUtf8("ipLabel"));
        ipLabel->setGeometry(QRect(160, 170, 111, 41));
        QFont font1;
        font1.setFamily(QString::fromUtf8("\345\276\256\350\275\257\351\233\205\351\273\221"));
        font1.setPointSize(16);
        ipLabel->setFont(font1);
        ipLineEdit = new QLineEdit(DemoQtClass);
        ipLineEdit->setObjectName(QString::fromUtf8("ipLineEdit"));
        ipLineEdit->setGeometry(QRect(310, 170, 311, 41));
        ipLineEdit->setStyleSheet(QString::fromUtf8(""));
        portLabel = new QLabel(DemoQtClass);
        portLabel->setObjectName(QString::fromUtf8("portLabel"));
        portLabel->setGeometry(QRect(130, 270, 141, 51));
        portLabel->setFont(font1);
        portLineEdit = new QLineEdit(DemoQtClass);
        portLineEdit->setObjectName(QString::fromUtf8("portLineEdit"));
        portLineEdit->setGeometry(QRect(310, 280, 311, 41));
        portLineEdit->setStyleSheet(QString::fromUtf8(""));
        horizontalLayoutWidget = new QWidget(DemoQtClass);
        horizontalLayoutWidget->setObjectName(QString::fromUtf8("horizontalLayoutWidget"));
        horizontalLayoutWidget->setGeometry(QRect(210, 370, 341, 80));
        horizontalLayout = new QHBoxLayout(horizontalLayoutWidget);
        horizontalLayout->setSpacing(6);
        horizontalLayout->setContentsMargins(11, 11, 11, 11);
        horizontalLayout->setObjectName(QString::fromUtf8("horizontalLayout"));
        horizontalLayout->setContentsMargins(0, 0, 0, 0);
        connectPushButton = new QPushButton(horizontalLayoutWidget);
        connectPushButton->setObjectName(QString::fromUtf8("connectPushButton"));
        QFont font2;
        font2.setFamily(QString::fromUtf8("\345\276\256\350\275\257\351\233\205\351\273\221"));
        font2.setPointSize(10);
        connectPushButton->setFont(font2);

        horizontalLayout->addWidget(connectPushButton);

        horizontalSpacer = new QSpacerItem(40, 20, QSizePolicy::Expanding, QSizePolicy::Minimum);

        horizontalLayout->addItem(horizontalSpacer);

        cancelPushButton = new QPushButton(horizontalLayoutWidget);
        cancelPushButton->setObjectName(QString::fromUtf8("cancelPushButton"));
        cancelPushButton->setFont(font2);

        horizontalLayout->addWidget(cancelPushButton);


        retranslateUi(DemoQtClass);

        QMetaObject::connectSlotsByName(DemoQtClass);
    } // setupUi

    void retranslateUi(QWidget *DemoQtClass)
    {
        DemoQtClass->setWindowTitle(QCoreApplication::translate("DemoQtClass", "DemoQt", nullptr));
        clientLabel->setText(QCoreApplication::translate("DemoQtClass", " \345\256\242\346\210\267\347\253\257", nullptr));
        ipLabel->setText(QCoreApplication::translate("DemoQtClass", "\346\234\215\345\212\241\345\231\250IP", nullptr));
        portLabel->setText(QCoreApplication::translate("DemoQtClass", "\346\234\215\345\212\241\345\231\250\347\253\257\345\217\243", nullptr));
        connectPushButton->setText(QCoreApplication::translate("DemoQtClass", "\350\277\236\346\216\245", nullptr));
        cancelPushButton->setText(QCoreApplication::translate("DemoQtClass", "\345\217\226\346\266\210", nullptr));
    } // retranslateUi

};

namespace Ui {
    class DemoQtClass: public Ui_DemoQtClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_DEMOQT_H
