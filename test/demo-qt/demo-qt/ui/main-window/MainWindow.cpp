#include "MainWindow.h"
#include "ui_MainWindow.h"

MainWindow::MainWindow(QWidget* parent)
	: QMainWindow(parent)
	, ui(new Ui::MainWindow()) {
	ui->setupUi(this);
	initUI();
}

MainWindow::~MainWindow() {
	delete ui;
}

void MainWindow::initUI() {
	buttonGroup = new QButtonGroup(this);
	QList<QPushButton*> btnList = findChildren<QPushButton*>();
	for (const auto& btn : btnList) {
		buttonGroup->addButton(btn);
	}

	connect(buttonGroup, static_cast<void (QButtonGroup::*)(QAbstractButton*)>(&QButtonGroup::buttonClicked), this, &MainWindow::onButtonGroupClicked);
	vec.resize(5);
}

void MainWindow::onButtonGroupClicked(QAbstractButton* btn) {
	double val = ui->lineEdit->text().toDouble();
	qInfo() << btn->text();
	QString name = btn->text();
	// 根据不同按钮的点击，来处理不同的逻辑
	// 如果是数字，直接显示出来
	if (name >= "0" && name <= "9" || name == ".") {
		// 清除一开始的0
		if (ui->lineEdit->text() == "0" && name != ".") {
			ui->lineEdit->clear();
		}
		if (vec[3] == "=") {
			vec.clear();
			vec.resize(5);
			ui->lineEdit->clear();
			ui->lineEdit_2->clear();
		}
		ui->lineEdit->insert(name);
	} else if (name == "+") {
		// 把左操作数和操作符存起来
		vec[0] = val;
		vec[1] = "+";
		ui->lineEdit->clear();
	} else if (name == "-") {
		vec[0] = val;
		vec[1] = "-";
		ui->lineEdit->clear();
	} else if (name == "x") {
		vec[0] = val;
		vec[1] = "x";
		ui->lineEdit->clear();
	} else if (name == "/") {
		vec[0] = val;
		vec[1] = "/";
		ui->lineEdit->clear();
	} else if (name == "=") {
		vec[2] = val;
		vec[3] = "=";
		if (vec[1] == "+") {
			vec[4] = vec[0].toDouble() + vec[2].toDouble();
		} else if (vec[1] == "-") {
			vec[4] = vec[0].toDouble() - vec[2].toDouble();
		} else if (vec[1] == "x") {
			vec[4] = vec[0].toDouble() * vec[2].toDouble();
		} else if (vec[1] == "/") {
			vec[4] = vec[0].toDouble() / vec[2].toDouble();
		}
		ui->lineEdit->setText(vec[4].toString());
	} else if (name == "C") {
		ui->lineEdit->clear();
		ui->lineEdit_2->clear();
		vec.clear();
		vec.resize(5);
	} else if (name == "CE") {
		ui->lineEdit->clear();
		vec.clear();
		vec.resize(5);
	} else if (name == "del") {
		ui->lineEdit->setCursorPosition(ui->lineEdit->cursorPosition() - 1);
		ui->lineEdit->del();
	}

	// 显示表达式
	ui->lineEdit_2->clear();
	for (int i = 0; i < vec.size() - 1; i++) {
		ui->lineEdit_2->insert(vec[i].toString());
	}
}
