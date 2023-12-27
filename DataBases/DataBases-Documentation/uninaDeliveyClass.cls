\NeedsTeXFormat{LaTeX2e}
\ProvidesClass{uninaDeliveyClass}[2023/12/01 Unina Delivery Class]
\LoadClass[a4paper]{article}

% Packages
\RequirePackage{graphicx}
\RequirePackage[a4paper, width=180mm, bottom=25mm, top=25mm, bindingoffset=6mm]{geometry}
\RequirePackage{fancyhdr}
\RequirePackage{xcolor}
\RequirePackage{amsmath, amssymb, amsthm, amsfonts}
\RequirePackage{mathtools}
\RequirePackage{hyperref}
\RequirePackage{titlesec}
\RequirePackage{baskervald}
\RequirePackage{tikz}
\RequirePackage{afterpage}
\RequirePackage{fontspec}
\RequirePackage{changepage}
\RequirePackage{float}
\RequirePackage{setspace}
\RequirePackage{lastpage}
\RequirePackage{titletoc}

\newcommand{\coursename}{Corso}
\newcommand{\ocoursename}[1]{\renewcommand{\coursename}{#1}}

\newcommand{\reportcity}{Corso}
\newcommand{\oreportcity}[1]{\renewcommand{\reportcity}{#1}}

\newcommand{\coursenamedue}{Corso}
\newcommand{\ocoursenamedue}[1]{\renewcommand{\coursenamedue}{#1}}

\newcommand{\reportyear}{Corso}
\newcommand{\oreportyear}[1]{\renewcommand{\reportyear}{#1}}

\newcommand{\groupnumber}{Corso}
\newcommand{\ogroupnumber}[1]{\renewcommand{\groupnumber}{#1}}

\newcommand{\titlelineuno}{Obiettivo Relazione}
\newcommand{\otitlelineuno}[1]{\renewcommand{\titlelineuno}{#1}}

\newcommand{\titlelinedue}{Obiettivo Relazione}
\newcommand{\otitlelinedue}[1]{\renewcommand{\titlelinedue}{#1}}

\newcommand{\mytitle}{Titolo relazione}
\newcommand{\omytitle}[1]{\renewcommand{\mytitle}{#1}}

\newcommand{\advisor}{Autori}
\newcommand{\oadvisor}[1]{\renewcommand{\advisor}{#1}}

\newcommand{\reportplace}{Napoli}
\newcommand{\oreportplace}[1]{\renewcommand{\reportplace}{#1}}

\newcommand{\reporttime}{\the\year} % Removed \Month from \reporttime
\newcommand{\oreporttime}[1]{\renewcommand{\reporttime}{#1}}

\makeatletter
\newcommand{\reporttype}{BASI DI DATI} % Changed \@reporttype to \reporttype
\newcommand{\oreporttype}[1]{\renewcommand{\reporttype}{#1}}
\makeatother

\oreporttype{BASI DI DATI}

\setmainfont{BauerBodoni}[
  Path = fonts/,
  Extension = .ttf,
  UprightFont = *-Regular,
  BoldFont = *-Bold,
  ItalicFont = *-Italic,
  BoldItalicFont = *-BoldItalic
]

% Apply the fancy page style to the entire document
\pagestyle{fancy}

\definecolor{pantoneRed}{HTML}{9B0014}
\definecolor{pantoneBlue}{HTML}{6667ab}

\def\reportlayout{

  \onehalfspacing

  \hypersetup{
    colorlinks=true,
    urlcolor=black,
    linkcolor=black,
    citecolor=FC3D21
  }

  \pagestyle{fancy}
  \renewcommand{\headrulewidth}{0.3pt}
  \renewcommand{\footrulewidth}{0.3pt}

%tutte le pagine
  \fancyhead{}
  \fancyhead[L]{
    \begin{tabular}{rl}
      \begin{picture}(25,15)(0,0)
        \put(0,-8){\includegraphics[width=8mm,height=8mm]{imgs/Universit_and__224__degli_Studi_di_Napoli_Federico_II-ai.pdf}}
      \end{picture}
      \begin{tabular}{l}
        \textbf{\bf \ttfamily Università degli studi di Napoli Federico II} \\
        \textbf{\bf \ttfamily Dipartimento di Ingegneria Elettrica e delle Tecnologie dell'Informazione}
      \end{tabular}
    \end{tabular}
  }
  \fancyhead[R]{
    \begin{tabular}{l}
      \tiny \bf \\
      \tiny \bf
    \end{tabular}
  }

  \fancyfoot{}
  \fancyfoot[L]{\scriptsize \ttfamily \oreporttype}
  \fancyfoot[R]{\scriptsize \ttfamily Page {\thepage}/\pageref{LastPage}}
}

\setlength{\headheight}{61pt}
\addtolength{\topmargin}{-40pt}