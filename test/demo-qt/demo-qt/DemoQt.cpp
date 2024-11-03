#include "DemoQt.h"

DemoQt::DemoQt(QWidget* parent) : QWidget(parent), ui(new Ui::DemoQtClass()) {
	ui->setupUi(this);
	connect(ui->cancelPushButton, &QPushButton::clicked, this, &DemoQt::onCancelPushButtonClicked);
	socket = new QTcpSocket(this);
	connect(ui->connectPushButton, &QPushButton::clicked, this, &DemoQt::onConnectPushButtonClicked);
}

DemoQt::~DemoQt() {
	delete ui;
}

void DemoQt::onCancelPushButtonClicked() {
	this->close();
}

void DemoQt::onConnectPushButtonClicked() {
	QString ip = ui->ipLineEdit->text();
	QString port = ui->portLineEdit->text();
	socket->connectToHost(QHostAddress(ip), port.toUShort());
	connect(socket, &QTcpSocket::connected, [this] {
		QMessageBox::information(this, "连接提示", "连接成功");
		});
	connect(socket, &QTcpSocket::disconnected, [this] {
		QMessageBox::warning(this, "连接提示", "断开连接");
		});
}