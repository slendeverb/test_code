#include "DemoQt.h"
#include <QtWidgets/QApplication>

int main(int argc, char* argv[]) {
	QApplication a(argc, argv);
	DemoQt w;
	w.show();
	return a.exec();
}
