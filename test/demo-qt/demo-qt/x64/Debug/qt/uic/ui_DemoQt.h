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
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_DemoQtClass
{
public:
    QVBoxLayout *verticalLayout;
    QHBoxLayout *horizontalLayout_2;
    QLabel *clientLabel;
    QHBoxLayout *horizontalLayout_3;
    QLabel *ipLabel;
    QSpacerItem *horizontalSpacer_2;
    QLineEdit *ipLineEdit;
    QHBoxLayout *horizontalLayout_4;
    QLabel *portLabel;
    QSpacerItem *horizontalSpacer_3;
    QLineEdit *portLineEdit;
    QHBoxLayout *horizontalLayout;
    QPushButton *connectPushButton;
    QSpacerItem *horizontalSpacer;
    QPushButton *cancelPushButton;

    void setupUi(QWidget *DemoQtClass)
    {
        if (DemoQtClass->objectName().isEmpty())
            DemoQtClass->setObjectName(QString::fromUtf8("DemoQtClass"));
        DemoQtClass->resize(721, 438);
        DemoQtClass->setStyleSheet(QString::fromUtf8("QLineEdit{\n"
"border:1px solid #6D7175;\n"
"border-radius:2px;\n"
"background-color:white;\n"
"height:20px;\n"
"font-size:20px;\n"
"color:#000000;\n"
"}"));
        verticalLayout = new QVBoxLayout(DemoQtClass);
        verticalLayout->setSpacing(6);
        verticalLayout->setContentsMargins(11, 11, 11, 11);
        verticalLayout->setObjectName(QString::fromUtf8("verticalLayout"));
        horizontalLayout_2 = new QHBoxLayout();
        horizontalLayout_2->setSpacing(6);
        horizontalLayout_2->setObjectName(QString::fromUtf8("horizontalLayout_2"));
        horizontalLayout_2->setSizeConstraint(QLayout::SetDefaultConstraint);
        horizontalLayout_2->setContentsMargins(300, -1, 300, -1);
        clientLabel = new QLabel(DemoQtClass);
        clientLabel->setObjectName(QString::fromUtf8("clientLabel"));
        QSizePolicy sizePolicy(QSizePolicy::Preferred, QSizePolicy::Fixed);
        sizePolicy.setHorizontalStretch(0);
        sizePolicy.setVerticalStretch(0);
        sizePolicy.setHeightForWidth(clientLabel->sizePolicy().hasHeightForWidth());
        clientLabel->setSizePolicy(sizePolicy);
        QFont font;
        font.setFamily(QString::fromUtf8("\345\276\256\350\275\257\351\233\205\351\273\221"));
        font.setPointSize(20);
        clientLabel->setFont(font);
        clientLabel->setAlignment(Qt::AlignCenter);

        horizontalLayout_2->addWidget(clientLabel);


        verticalLayout->addLayout(horizontalLayout_2);

        horizontalLayout_3 = new QHBoxLayout();
        horizontalLayout_3->setSpacing(6);
        horizontalLayout_3->setObjectName(QString::fromUtf8("horizontalLayout_3"));
        horizontalLayout_3->setContentsMargins(100, -1, 100, -1);
        ipLabel = new QLabel(DemoQtClass);
        ipLabel->setObjectName(QString::fromUtf8("ipLabel"));
        QFont font1;
        font1.setFamily(QString::fromUtf8("\345\276\256\350\275\257\351\233\205\351\273\221"));
        font1.setPointSize(16);
        ipLabel->setFont(font1);

        horizontalLayout_3->addWidget(ipLabel);

        horizontalSpacer_2 = new QSpacerItem(40, 20, QSizePolicy::Preferred, QSizePolicy::Minimum);

        horizontalLayout_3->addItem(horizontalSpacer_2);

        ipLineEdit = new QLineEdit(DemoQtClass);
        ipLineEdit->setObjectName(QString::fromUtf8("ipLineEdit"));
        QSizePolicy sizePolicy1(QSizePolicy::Expanding, QSizePolicy::Fixed);
        sizePolicy1.setHorizontalStretch(0);
        sizePolicy1.setVerticalStretch(0);
        sizePolicy1.setHeightForWidth(ipLineEdit->sizePolicy().hasHeightForWidth());
        ipLineEdit->setSizePolicy(sizePolicy1);
        ipLineEdit->setMinimumSize(QSize(0, 30));
        ipLineEdit->setStyleSheet(QString::fromUtf8(""));

        horizontalLayout_3->addWidget(ipLineEdit);


        verticalLayout->addLayout(horizontalLayout_3);

        horizontalLayout_4 = new QHBoxLayout();
        horizontalLayout_4->setSpacing(6);
        horizontalLayout_4->setObjectName(QString::fromUtf8("horizontalLayout_4"));
        horizontalLayout_4->setContentsMargins(100, -1, 100, -1);
        portLabel = new QLabel(DemoQtClass);
        portLabel->setObjectName(QString::fromUtf8("portLabel"));
        portLabel->setFont(font1);

        horizontalLayout_4->addWidget(portLabel);

        horizontalSpacer_3 = new QSpacerItem(12, 20, QSizePolicy::Preferred, QSizePolicy::Minimum);

        horizontalLayout_4->addItem(horizontalSpacer_3);

        portLineEdit = new QLineEdit(DemoQtClass);
        portLineEdit->setObjectName(QString::fromUtf8("portLineEdit"));
        portLineEdit->setEnabled(true);
        sizePolicy1.setHeightForWidth(portLineEdit->sizePolicy().hasHeightForWidth());
        portLineEdit->setSizePolicy(sizePolicy1);
        portLineEdit->setMinimumSize(QSize(0, 30));
        portLineEdit->setStyleSheet(QString::fromUtf8(""));

        horizontalLayout_4->addWidget(portLineEdit);


        verticalLayout->addLayout(horizontalLayout_4);

        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setSpacing(6);
        horizontalLayout->setObjectName(QString::fromUtf8("horizontalLayout"));
        horizontalLayout->setContentsMargins(200, -1, 200, -1);
        connectPushButton = new QPushButton(DemoQtClass);
        connectPushButton->setObjectName(QString::fromUtf8("connectPushButton"));
        QFont font2;
        font2.setFamily(QString::fromUtf8("\345\276\256\350\275\257\351\233\205\351\273\221"));
        font2.setPointSize(10);
        connectPushButton->setFont(font2);

        horizontalLayout->addWidget(connectPushButton);

        horizontalSpacer = new QSpacerItem(40, 20, QSizePolicy::Expanding, QSizePolicy::Minimum);

        horizontalLayout->addItem(horizontalSpacer);

        cancelPushButton = new QPushButton(DemoQtClass);
        cancelPushButton->setObjectName(QString::fromUtf8("cancelPushButton"));
        cancelPushButton->setFont(font2);

        horizontalLayout->addWidget(cancelPushButton);


        verticalLayout->addLayout(horizontalLayout);


        retranslateUi(DemoQtClass);

        QMetaObject::connectSlotsByName(DemoQtClass);
    } // setupUi

    void retranslateUi(QWidget *DemoQtClass)
    {
        DemoQtClass->setWindowTitle(QCoreApplication::translate("DemoQtClass", "DemoQt", nullptr));
        clientLabel->setText(QCoreApplication::translate("DemoQtClass", "\345\256\242\346\210\267\347\253\257", nullptr));
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
