#include <iostream>
#include <string>
#include <cstdlib>
using namespace std;

// written by Greg Willard (r3pwn)

void printHeader(void);
void DEBUG(string var1, string var2, string var3);

int main(void)
{
    start:
    string firstName, lastName, email;
    printHeader();
    cout << "Enter your first and last name: ";
    cin >> firstName >> lastName;
    cout << "Enter your email address: ";
    cin >> email;
    // TODO: submit data to server (hacky way: use 'curl')
    DEBUG(firstName, lastName, email);
    cout << "\nThank you for signing in, and enjoy the hackathon!\n";
    system("sleep 7");
    system("clear");
    goto start;
}

void printHeader(void)
{
    cout << "__________________  .____    _____.___. ___ ___    _____  _________  ____  __.  _________\n";
    cout << "\\______   \\_____  \\ |    |   \\__  |   |/   |   \\  /  _  \\ \\_   ___ \\|    |/ _| /   _____/\n";
    cout << " |     ___//   |   \\\|    |    /   |   /    ~    \\/  /_\\  \\/    \\  \\/|      <   \\_____  \\\n";
    cout << " |    |   /    |    \\    |___ \\____   \\    Y    /    |    \\     \\___|    |  \\  /        \\\n";
    cout << " |____|   \\_______  /_______ \\/ ______|\\___|_  /\\____|__  /\\______  /____|__ \\/_______  /\n";
    cout << "                  \\/        \\/\\/             \\/         \\/        \\/        \\/        \\/\n";
    cout << "                             _______________  _____________\n";
    cout << "                             \\_____  \\   _  \\/_   \\______  \\\n";
    cout << "                              /  ____/  /_\\  \\|   |   /    /\n";
    cout << "                             /       \\  \\_/   \\   |  /    /\n";
    cout << "                             \\_______ \\_____  /___| /____/\n";
    cout << "                                     \\/     \\/\n\n";
}

void DEBUG(string firstName, string lastName, string email)
{
    cout << "\nDEBUG: firstName: " << firstName; 
    cout << "\nDEBUG: lastName: " << lastName;
    cout << "\nDEBUG: email: " << email << endl;
}
