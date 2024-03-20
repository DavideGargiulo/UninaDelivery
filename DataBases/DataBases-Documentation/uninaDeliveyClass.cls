\NeedsTeXFormat{LaTeX2e}
\ProvidesClass{uninaDeliveyClass}[2023/12/01 Unina Delivery Class]
\LoadClass{article}

% Packages
\RequirePackage{graphicx}                                                                     % Package for including graphics in your document
\RequirePackage[a4paper, width=180mm, bottom=25mm, top=25mm, bindingoffset=6mm]{geometry}     % Package for specifying the page layout
\RequirePackage{fancyhdr}                                                                     % Package for customizing page headers and footers
\RequirePackage[table]{xcolor}                                                                       % Package for using colors in your document
\RequirePackage{amsmath, amssymb, amsthm, amsfonts}                                           % Package Packages for advanced mathematical typesetting
\RequirePackage{mathtools}                                                                    % Extension to AMS Math, providing additional tools
\RequirePackage{hyperref}                                                                     % Package for creating hyperlinks in the document
\RequirePackage{titlesec}                                                                     % Package for customizing section titles
\RequirePackage{tikz}                                                                         % Package for creating graphics and diagrams
\RequirePackage{afterpage}                                                                    % Package for executing commands after the current page
\RequirePackage{fontspec}                                                                     % Package for configuring and loading fonts
\RequirePackage{changepage}                                                                   % Package for changing the layout of individual pages
\RequirePackage{float}                                                                        % Package for customizing the appearance of floats (e.g., figures and tables)
\RequirePackage{setspace}                                                                     % Package for setting line spacing in the document
\RequirePackage{lastpage}                                                                     % Package for referring to the last page of the document
\RequirePackage{titletoc}                                                                     % Package for customizing the table of contents
\RequirePackage{adjustbox}                                                                    % Package for adjusting the size of the content
\RequirePackage{enumitem}                                                                     % Package for customizing lists
\RequirePackage{pdfpages}                                                                     % Package for including PDF pages
\RequirePackage{tabularx}                                                                     % Package for creating tabular environments
\RequirePackage{ifthen}                                                                       % Package for conditionals
\RequirePackage{array}                                                                        % Package for extending the array and tabular environments
\RequirePackage{colortbl}                                                                     % Package for adding color to tables
\RequirePackage{xparse}                                                                       % Package for defining commands with many optional arguments
\RequirePackage{environ}                                                                      % Package for defining environments with many optional arguments
\RequirePackage{booktabs}                                                                     % Package for enhancing the quality of tables
\RequirePackage{colortbl}                                                                     % Package for adding color to tables
\RequirePackage{listings}                                                                     % Package for including source code in the document

\newcommand{\coursename}{Corso}                                                               % Course Name
\newcommand{\ocoursename}[1]{\renewcommand{\coursename}{#1}}

\newcommand{\reportcity}{Corso}                                                               % Report City
\newcommand{\oreportcity}[1]{\renewcommand{\reportcity}{#1}}

\newcommand{\coursenamedue}{Corso}                                                            % Second Course Name
\newcommand{\ocoursenamedue}[1]{\renewcommand{\coursenamedue}{#1}}

\newcommand{\reportyear}{Corso}                                                               % Report Year
\newcommand{\oreportyear}[1]{\renewcommand{\reportyear}{#1}}

\newcommand{\groupnumber}{Corso}                                                              % Group Number
\newcommand{\ogroupnumber}[1]{\renewcommand{\groupnumber}{#1}}

\newcommand{\titlelineuno}{Obiettivo Relazione}                                               % First Title Line
\newcommand{\otitlelineuno}[1]{\renewcommand{\titlelineuno}{#1}}

\newcommand{\titlelinedue}{Obiettivo Relazione}                                               % Second Title Line
\newcommand{\otitlelinedue}[1]{\renewcommand{\titlelinedue}{#1}}

\newcommand{\mytitle}{Titolo relazione}                                                       % Report Title
\newcommand{\omytitle}[1]{\renewcommand{\mytitle}{#1}}

\newcommand{\advisor}{Autori}                                                                 % Advisor
\newcommand{\oadvisor}[1]{\renewcommand{\advisor}{#1}}

\newcommand{\reportplace}{Napoli}                                                             % Report Place
\newcommand{\oreportplace}[1]{\renewcommand{\reportplace}{#1}}

\newcommand{\reporttime}{\the\year}                                                           % Report Time
\newcommand{\oreporttime}[1]{\renewcommand{\reporttime}{#1}}

\makeatletter
\newcommand{\reporttype}{BASI DI DATI}                                                        % Report Type (using makeatletter to handle @ symbol)
\newcommand{\oreporttype}[1]{\renewcommand{\reporttype}{#1}}
\makeatother

\oreporttype{BASI DI DATI}

\setmainfont{BauerBodoni}[                                                                    % Set the main font to BauerBodoni
  Path = fonts/,                                                                              % Set the path to the directory containing the font files
  Extension = .ttf,                                                                           % Specify the file extension for the font files
  UprightFont = *-Regular,                                                                    % Specify the font for the upright (normal) style
  BoldFont = *-Bold,                                                                          % Specify the font for the bold style
  ItalicFont = *-Italic,                                                                      % Specify the font for the italic style
  BoldItalicFont = *-BoldItalic                                                               % Specify the font for the bold italic style
]

\pagestyle{fancy}                                                                             % Set page style to 'fancy'

\definecolor{pantoneRed}{HTML}{9B0014}                                                        % Define color pantoneRed using HTML notation
\definecolor{pantoneBlue}{HTML}{6667ab}                                                       % Define color pantoneBlue using HTML notation

\def\reportlayout{                                                                            % Define a command to set the report layout

  \onehalfspacing                                                                             % Set line spacing to one and a half

  \hypersetup{                                                                                % Hyperref setup for links and colors
    colorlinks=true,
    urlcolor=black,
    linkcolor=black,
    citecolor=FC3D21
  }

  \pagestyle{fancy}                                                                           % Set fancy page style with rules
  \renewcommand{\headrulewidth}{0.3pt}
  \renewcommand{\footrulewidth}{0.3pt}

  \fancyhead{}                                                                                % Header and footer content for all pages
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

  \fancyfoot{}                                                                                % Footer content for all pages
  \fancyfoot[L]{\scriptsize \ttfamily \oreporttype}
  \fancyfoot[R]{\scriptsize \ttfamily Page {\thepage}/\pageref{LastPage}}
}

\setlength{\headheight}{61pt}                                                                 % Set the height of the header
\addtolength{\topmargin}{-40pt}                                                               % Adjust the top margin to create more space for the header

\setcounter{secnumdepth}{3}                                                                   % Set the depth of section numbering to include subsubsections

\titleformat{\section}                                                                        % Format and spacing for section headings
  {\Huge\bfseries\color{pantoneRed}}
  {\thesection}
  {10pt}
  {\Huge}
\titlespacing*{\section}{0pt}{30pt}{0pt}

\titleformat{\subsection}                                                                     % Format and spacing for subsection headings
  {\Large\bfseries\color{black}}
  {\thesubsection}
  {10pt}
  {\Large}
\titlespacing*{\subsection}{0pt}{0pt}{0pt}

\titleformat{\subsubsection}                                                                  % Format and spacing for subsubsection headings
  {\Large\bfseries\color{black}}
  {\thesubsubsection}
  {1em} % Space between the label and the title
  {}
  {\Large}
\titlespacing*{\subsubsection}{0pt}{00pt}{0pt}

\makeatletter
        %% Global
        \setlength\parindent{0pt}
        \setlength\parskip{0.75em}
        \setlength{\headsep}{0.5cm}

        %% Define no Par Skip
        \let\svpar\par
        \edef\svparskip{\the\parskip}
        \def\revertpar{\svpar\setlength\parskip{\svparskip}\let\par\svpar}
        \def\noparskip{\leavevmode\setlength\parskip{0pt}\def\par{\svpar\let\par\revertpar}}
\makeatother

\newcolumntype{Y}{>{\centering\arraybackslash}X}
\definecolor{lightgray}{gray}{0.9}

\definecolor{dkgreen}{rgb}{0,0.6,0}
\definecolor{ltgray}{rgb}{0.5,0.5,0.5}
\lstset{%
  	backgroundcolor=\color{white},
  	basicstyle=\small,
  	breakatwhitespace=false,
  	breaklines=true,
  	captionpos=b,
  	commentstyle=\color{dkgreen},
  	deletekeywords={...},
  	escapeinside={\%*}{*)},
  	extendedchars=true,
  	frame=single,
  	keepspaces=true,
  	keywordstyle=\color{blue},
  	language=SQL,
  	morekeywords={*,modify,MODIFY,...},
  	numbers=left,
  	numbersep=15pt,
  	numberstyle=\tiny,
  	rulecolor=\color{ltgray},
  	showspaces=false,
  	showstringspaces=false,
  	showtabs=false,
  	stepnumber=1,
  	tabsize=4,
  	title=\lstname
}