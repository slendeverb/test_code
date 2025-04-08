#pragma once

#include "ui_demo_qt.h"
#include <QMainWindow>
#include <QtCharts>
#include <QTextEdit>

enum Operator{
    None,
    Plus,
    Minus,
    Multiply,
    Divide,
};

class demo_qt final : public QMainWindow{
    Q_OBJECT
    
public:
    explicit demo_qt(QWidget* parent = nullptr);
    ~demo_qt() override;

private Q_SLOTS:
    void pressOperator(Operator op=None);
    void pressEqual();
    void pressNumber(int i) const;
    void pressDot() const;
    void pressBackspace() const;

    void on_open_triggered();
    void on_save_triggered();
    void on_saveAs_triggered();
    void on_autoNewLine_triggered(bool checked) const;
    void on_edit_textChanged();

Q_SIGNALS:

protected:
    void closeEvent(QCloseEvent *event) override;

private:
    Ui_demo_qt* ui;
    QLineEdit* m_lineResult{};

    double m_lastValue=0;
    Operator m_lastOperator=None;

    static void setLineText(QLineEdit* line, QString&& text);

    QTextEdit* m_edit;
    QString m_currentPath;

    void saveFile(const QTextEdit *textEdit, const QString& path);
    void openFile(QTextEdit* textEdit, const QString& path);

    bool m_hasUnsavedChanges=false;

    bool checkUnsavedModifications();
};

class Label final : public QLabel{
    Q_OBJECT

public:
    explicit Label(QWidget* parent=nullptr)
        : QLabel(parent)
    {
        setAlignment(Qt::AlignCenter);
        setStyleSheet(R"(
                    QLabel {
                    margin: 20px;
                    font-size: 40px;
                    })");
    }
};
