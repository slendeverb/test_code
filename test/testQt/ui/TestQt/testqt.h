//
// Created by slendeverb on 2024/11/20.
//

#ifndef TESTQT_TESTQT_H
#define TESTQT_TESTQT_H

#include <QWidget>
#include <QMainWindow>
#include <QApplication>

QT_BEGIN_NAMESPACE
namespace Ui { class TestQt; }
QT_END_NAMESPACE

class TestQt : public QWidget {
Q_OBJECT

public:
    explicit TestQt(QWidget *parent = nullptr);

    ~TestQt() override;

private:
    Ui::TestQt *ui;
};


#endif //TESTQT_TESTQT_H
