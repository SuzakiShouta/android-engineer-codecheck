# 株式会社ゆめみ Android エンジニアコードチェック課題

## 概要

本プロジェクトは株式会社ゆめみ様のAndroidエンジニアを希望する人に向けた課題です。

## アプリ仕様

本アプリは GitHub のリポジトリを検索するアプリです。

<img src="https://user-images.githubusercontent.com/86464601/223680264-f20ea074-9aec-43f9-b266-b203c8a3eacf.gif" width="320">

### 環境

- IDE：Android Studio Electric Eel | 2022.1.1 Patch 2
- Kotlin：1.8.0
- Java：8
- Gradle：7.4.2
- minSdk：23
- targetSdk：33

### 動作

1. 何かしらのキーワードを入力
2. GitHub API（`search/repositories`）でリポジトリを検索し、結果一覧を概要（リポジトリ名）で表示
3. 特定の結果を選択したら、該当リポジトリの詳細（リポジトリ名、オーナーアイコン、プロジェクト言語、Star 数、Watcher 数、Fork 数、Issue 数）を表示

## バージョン1.0.1
- アプリの安全性が向上しました。
- 幾つかのバグが修正されました。
- レイアウトが見やすくなりました。
- 詳細画面から検索画面に戻っても検索結果が保持されるようになりました。

## 反省点や聞きたい点

### github
いつもは個人で開発しているためブランチ運用などを意識するのは初めてでした。  
githubのWeb操作で幾つかの失敗をしてしまいました。
- 反省点
  - 当初コミットメッセージを英語にしていた。
    - ターミナルで入力していたため、漢字変換のEnter入力の誤爆が怖かった。
    - 途中からコミットとブランチの確認にForkというツールを使った。
  - 最後mainブランチへのマージで失敗してしまった。対応が合っているかどうかもわからない。
- 聞きたい点
  - コミットメッセージやプルリクに書く情報が適切だったかどうか。
  - issueやブランチ運用が適切だったかどうか。

### ソースコードや環境
- 反省点
  - recyclerViewとadapterの理解が薄かった。正しく書けている自身があまりない。
  - ずっとJava8で作業していた。修正する時間はないと判断したためJava11でどうなるかわからない。
- 聞きたい点
  - この規模のアプリにこのパッケージ（フォルダ）分けは過剰ではないか。
  - FragmentやViewModelの仕事は適切か。
  - #21 で検索結果をMainApplicationに保管するようにしたが問題は無いか。
