#pragma once

#include <QMainWindow>
#include <QButtonGroup>
#include <QPushButton>
#include <QList>
#include <QAbstractButton>
#include <QVector>
#include <QDebug>

namespace Ui {
	class MainWindow;
}

class MainWindow : public QMainWindow {
	Q_OBJECT

public:
	explicit MainWindow(QWidget* parent = nullptr);
	~MainWindow();

private slots:
	void onButtonGroupClicked(QAbstractButton* btn);

private:
	void initUI();
	Ui::MainWindow* ui;
	QButtonGroup* buttonGroup;
	QVector<QVariant> vec;
};
