import 'package:detect_magisk1/detect_magisk1.dart';
import 'package:flutter/material.dart';

void main() {
  WidgetsFlutterBinding.ensureInitialized();

  runApp(const App());
}

class App extends StatelessWidget {
  const App({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(brightness: Brightness.light),
      home: const Home(),
    );
  }
}

class Home extends StatefulWidget {
  const Home({Key? key}) : super(key: key);

  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {
  late final Future<bool> _check = DetectMagisk1.check();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: FutureBuilder<bool>(
        future: _check,
        builder: (context, snapshot) {
          switch (snapshot.connectionState) {
            case ConnectionState.none:
            case ConnectionState.waiting:
              return const Center(
                child: CircularProgressIndicator.adaptive(),
              );

            case ConnectionState.active:
            case ConnectionState.done:
              if (snapshot.hasError) {
                final error = snapshot.error!;
                return Center(
                  child: Text(
                    error.toString(),
                  ),
                );
              }

              final hasMagisk = snapshot.data as bool;

              return Center(
                child: hasMagisk
                    ? const Text('Magisk detected!')
                    : const Text('No magisk detected'),
              );
          }
        },
      ),
    );
  }
}
