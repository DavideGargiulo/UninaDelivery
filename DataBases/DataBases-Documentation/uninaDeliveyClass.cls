\NeedsTeXFormat{LaTeX2e}
\ProvidesClass{uninaDeliveyClass}[2023/12/01 Unina Delivery Class]
\LoadClass[14pt]{extreport}

% Packages
\RequirePackage{graphicx}                                                                     % Required for including images
\RequirePackage[a4paper, width=180mm, bottom=25mm, top=25mm, bindingoffset=6mm]{geometry}     % Page margins
\RequirePackage{fancyhdr}                                                                     % Headers and footers                                                                         % All pages have headers and footers
\RequirePackage{xcolor}                                                                       % Required for specifying colors by name
\RequirePackage{amsmath, amssymb, amsthm, amsfonts}                                           % Required for math
\RequirePackage{mathtools}                                                                    % Required for math
\RequirePackage{hyperref}                                                                     % Required for links
\RequirePackage{titlesec}                                                                     % Required for titles
\RequirePackage{lmodern}                                                                      % Required for the font
\RequirePackage{tikz}                                                                         % Required for drawing custom shapes
\RequirePackage{afterpage}                                                                    % Required for forcing a figure to the next page

\renewcommand*\familydefault{\sfdefault} % Change to Sans Serif font
\AtBeginDocument{\usefont{OT1}{lmss}{m}{n}} % Change to Latin Modern Sans Serif font at the beginning of the document

% Apply the fancy page style to the entire document
\pagestyle{fancy}

\definecolor{pantone}{HTML}{9B0014} % Pantone 1805

\renewcommand{\maketitle}{
  \pagecolor{pantone}
  \begin{titlepage}
    \vfill % Push the content to the bottom
    \begin{center}
      \begin{minipage}{.2\textwidth}
        \includegraphics[width=\linewidth]{imgs/Universit_and__224__degli_Studi_di_Napoli_Federico_II-ai.png} % Replace with your image path
        {\color{white}\large\textsc{Università\\degli Studi \\di Napoli}}
      \end{minipage}
    \end{center}
  \end{titlepage}
  \afterpage{\pagecolor{white}}
}