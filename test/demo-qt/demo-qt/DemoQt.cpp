#include "DemoQt.h"

DemoQt::DemoQt(QWidget* parent) : QWidget(parent), ui(new Ui::DemoQtClass()) {
	ui->setupUi(this);

}

DemoQt::~DemoQt() {
	delete ui;
}
