#pragma once

#include <QWidget>
#include "ui_DemoQt.h"
#include <QProcess>
#include <QButtonGroup>
#include <QMessageBox>
#include <QFileDialog>
#include <QDebug>
#include <QKeyEvent>
#include <QMouseEvent>
#include <QtCharts>
#include <QTcpSocket>
#include <QHostAddress>

QT_BEGIN_NAMESPACE
namespace Ui { class DemoQtClass; };
QT_END_NAMESPACE

class DemoQt : public QWidget {
	Q_OBJECT

public:
	DemoQt(QWidget* parent = nullptr);
	~DemoQt();

private slots:
	void onConnectPushButtonClicked();
	void onCancelPushButtonClicked();

private:
	Ui::DemoQtClass* ui;
	QTcpSocket* socket;
};
