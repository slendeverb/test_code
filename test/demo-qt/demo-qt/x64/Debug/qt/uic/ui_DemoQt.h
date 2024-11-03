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
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_DemoQtClass
{
public:

    void setupUi(QWidget *DemoQtClass)
    {
        if (DemoQtClass->objectName().isEmpty())
            DemoQtClass->setObjectName(QString::fromUtf8("DemoQtClass"));
        DemoQtClass->resize(600, 400);

        retranslateUi(DemoQtClass);

        QMetaObject::connectSlotsByName(DemoQtClass);
    } // setupUi

    void retranslateUi(QWidget *DemoQtClass)
    {
        DemoQtClass->setWindowTitle(QCoreApplication::translate("DemoQtClass", "DemoQt", nullptr));
    } // retranslateUi

};

namespace Ui {
    class DemoQtClass: public Ui_DemoQtClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_DEMOQT_H
