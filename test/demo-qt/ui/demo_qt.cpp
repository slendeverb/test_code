#include "demo_qt.h"

demo_qt::demo_qt(QWidget* parent)
    : QMainWindow(parent)
    , ui(new Ui_demo_qt)
{
    ui->setupUi(this);

    setWindowTitle("notepad");
    setGeometry(200,200,1200,700);

    auto* menuBar=new QMenuBar(this);
    setMenuBar(menuBar);

    m_edit=new QTextEdit(this);
    m_edit->setObjectName("edit");
    setCentralWidget(m_edit);

    QMenu* menu=menuBar->addMenu("文件");

    QAction* action=menu->addAction("打开");
    action->setObjectName("open");
    action=menu->addAction("保存");
    action->setObjectName("save");
    action=menu->addAction("另存为");
    action->setObjectName("saveAs");

    menu=menuBar->addMenu("编辑");
    action=menu->addAction("自动换行");
    action->setObjectName("autoNewLine");
    action->setCheckable(true);

    // QSettings settings("stwings","03_notepad",this);
    constexpr bool enableAutoNewLine=true;
    // enableAutoNewLine=settings.value("enableAutoNewLine",true).toBool();
    action->setChecked(enableAutoNewLine);
    on_autoNewLine_triggered(enableAutoNewLine);

    QMetaObject::connectSlotsByName(this);
}

bool demo_qt::checkUnsavedModifications() {
    if (m_hasUnsavedChanges) {
        const auto& stdBtn =
           QMessageBox::question(this,"notepad","您有未保存的内容，是否保存？",
               QMessageBox::Save|QMessageBox::Discard|QMessageBox::Cancel);
        switch (stdBtn) {
            case QMessageBox::Save: {
                on_save_triggered();
                break;
            }
            case QMessageBox::Discard: {
                break;
            }
            case QMessageBox::Cancel: {
                return true;
            }
            default: {
                break;
            }
        }
    }
    return false;
}

void demo_qt::closeEvent(QCloseEvent *event) {
    if (checkUnsavedModifications()) {
        event->ignore();
    }else {
        QMainWindow::closeEvent(event);
    }
}

void demo_qt::on_edit_textChanged() {
    this->m_hasUnsavedChanges=true;
}

void demo_qt::on_autoNewLine_triggered(const bool checked) const {
    if (checked) {
        m_edit->setLineWrapMode(QTextEdit::WidgetWidth);
    }else {
        m_edit->setLineWrapMode(QTextEdit::NoWrap);
    }
    // QSettings settings("stwings","03_notepad",this);
    // settings.setValue("enableAutoNewLine",checked);
}

void demo_qt::on_open_triggered() {
    if (checkUnsavedModifications()) {
        return;
    }

    const QString path=QFileDialog::getOpenFileName(this,"选择文件打开","",
        "文本文件 (*.txt *.log);;所有文件 (*)");
    if (path.isEmpty()) {
        return;
    }
    openFile(m_edit, path);
}

void demo_qt::on_save_triggered() {
    if (m_currentPath.isEmpty()) {
        on_saveAs_triggered();
    }else {
        saveFile(m_edit,m_currentPath);
    }
}

void demo_qt::on_saveAs_triggered() {
    const QString path=QFileDialog::getSaveFileName(this,"保存文件","",
        "文本文件 (*.txt;*.log);;所有文件 (*)");
    if (path.isEmpty()) {
        return;
    }
    saveFile(m_edit,path);
}

void demo_qt::openFile(QTextEdit* textEdit, const QString& path) {
    QFile file(path);
    if (!file.open(QFile::ReadOnly)) {
        qWarning()<<"file open error";
        return;
    }
    const QString text=QString::fromUtf8(file.readAll());
    textEdit->setPlainText(text);
    m_currentPath=path;
    file.close();
    m_hasUnsavedChanges=false;
}

void demo_qt::saveFile(const QTextEdit* textEdit, const QString& path) {
    QFile file(path);
    if (!file.open(QFile::WriteOnly)) {
        qWarning()<<"file open error";
    }
    const QString text=textEdit->toPlainText();
    file.write(text.toUtf8());
    file.close();
    m_hasUnsavedChanges=false;
}

void demo_qt::setLineText(QLineEdit* line, QString&& text) {
    if (int pos=0; line->validator()->validate(text,pos)==QValidator::Invalid) {
        return;
    }
    while (text.size()>=2 && text[0]=='0' && (text[1]>='0' && text[1]<='9')) {
        text.remove(0,1);
    }
    line->setText(text);
}

void demo_qt::pressDot() const {
    QString text=m_lineResult->text();
    text+='.';
    setLineText(m_lineResult,std::move(text));
}

void demo_qt::pressBackspace() const {
    QString text=m_lineResult->text();
    if (!text.isEmpty()) {
        text.remove(text.size()-1,1);
    }
    setLineText(m_lineResult,std::move(text));
}

void demo_qt::pressNumber(const int i) const {
    QString text=m_lineResult->text();
    text+=QString::number(i);
    setLineText(m_lineResult,std::move(text));
}

void demo_qt::pressOperator(const Operator op){
    const double result=m_lineResult->text().toDouble();
    m_lastValue=result;
    m_lastOperator=op;
    setLineText(m_lineResult,"");
}

void demo_qt::pressEqual(){
    const auto value1=m_lastValue;
    const auto value2=m_lineResult->text().toDouble();
    double result=0;
    switch(m_lastOperator){
        case None: {
            result=value2;
            break;
        }
        case Plus: {
            result=value1+value2;
            break;
        }
        case Minus: {
            result=value1-value2;
            break;
        }
        case Multiply: {
            result=value1*value2;
            break;
        }
        case Divide: {
            result=value1/value2;
            break;
        }
        default: {
            break;
        }
    }
    m_lastOperator=None;
    setLineText(m_lineResult,QString::number(result,'g',10));
}

demo_qt::~demo_qt(){
    delete ui;
}
