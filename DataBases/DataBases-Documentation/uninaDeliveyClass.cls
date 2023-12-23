\NeedsTeXFormat{LaTeX2e}
\ProvidesClass{uninaDeliveyClass}[2023/12/01 Unina Delivery Class]
\LoadClass[14pt]{extreport}

% Packages
\RequirePackage{graphicx}                                                                     % Required for including images
\RequirePackage[a4paper, width=180mm, bottom=25mm, top=25mm, bindingoffset=6mm]{geometry}     % Page margins
\RequirePackage{fancyhdr}                                                                     % Headers and footers
\pagestyle{fancy}                                                                             % All pages have headers and footers
\RequirePackage{xcolor}                                                                       % Required for specifying colors by name
\RequirePackage{amsmath, amssymb, amsthm, amsfonts}                                           % Required for math
\RequirePackage{mathtools}                                                                    % Required for math
\RequirePackage{hyperref}                                                                     % Required for links
\RequirePackage{titlesec}                                                                     % Required for titles

% Default color
\definecolor{mycolor}{HTML}{cc4400}

% Header and footer setup
\fancyhf{}
\fancyhead[L]{\leftmark}
\fancyfoot[C]{\thepage}
\setlength{\headheight}{17.0pt} % Set the height of the header

% Title format
\titleformat{\section}
  {\normalfont\Large\bfseries\color{mycolor}}
  {\thesection}
  {1em}
  {}

\titleformat{\subsection}
  {\normalfont\large\bfseries\color{mycolor}}
  {\thesubsection}
  {1em}
  {}

% Title page
\renewcommand{\maketitle}{
  \begin{titlepage}
    \centering
    {\Huge\bfseries\@title\par}
    \vskip 2em%
    {\Large\@author\par}
    \vskip 1em%
    {\@date\par}
  \end{titlepage}
}