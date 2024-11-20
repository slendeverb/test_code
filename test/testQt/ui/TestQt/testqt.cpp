//
// Created by slendeverb on 2024/11/20.
//

// You may need to build the project (run Qt uic code generator) to get "ui_TestQt.h" resolved

#include "testqt.h"
#include "ui_TestQt.h"


TestQt::TestQt(QWidget *parent) :
        QWidget(parent), ui(new Ui::TestQt) {
    ui->setupUi(this);
}

TestQt::~TestQt() {
    delete ui;
}
