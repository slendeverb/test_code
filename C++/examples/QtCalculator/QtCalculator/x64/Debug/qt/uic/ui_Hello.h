/********************************************************************************
** Form generated from reading UI file 'Hello.ui'
**
** Created by: Qt User Interface Compiler version 5.14.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_HELLO_H
#define UI_HELLO_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_HelloClass
{
public:

    void setupUi(QWidget *HelloClass)
    {
        if (HelloClass->objectName().isEmpty())
            HelloClass->setObjectName(QString::fromUtf8("HelloClass"));
        HelloClass->resize(600, 400);

        retranslateUi(HelloClass);

        QMetaObject::connectSlotsByName(HelloClass);
    } // setupUi

    void retranslateUi(QWidget *HelloClass)
    {
        HelloClass->setWindowTitle(QCoreApplication::translate("HelloClass", "Hello", nullptr));
    } // retranslateUi

};

namespace Ui {
    class HelloClass: public Ui_HelloClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_HELLO_H
