//
//  JlptResourceScreen.swift
//  iosApp
//
//  Created by Suraj GP on 10/05/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct JlptResourceScreen: View {
    
    var body: some View {
        ZStack {
            
            Color.Neomorph.main
                .edgesIgnoringSafeArea(.all)
            
            VStack {
                AppTopBar(navigateUpIcon: "arrow.backward", title: "JLPT")
                    .padding()
                
                VStack(spacing: 24) {
                    NavigationLink {
                        JlptListScreen(level: 5)
                    } label: {
                        JlptRow(title: "N5", description: "Understanding of some Basic Japanese")
                    }
                    
                    NavigationLink {
                        JlptListScreen(level: 4)
                    } label: {
                        JlptRow(title: "N4", description: "Understanding of Basic Japanese")
                    }
                    
                    NavigationLink {
                        JlptListScreen(level: 3)
                    } label: {
                        JlptRow(title: "N3", description: "Understanding of some Japanese used in everyday situations to some degree")
                    }
                    
                    NavigationLink {
                        JlptListScreen(level: 2)
                    } label: {
                        JlptRow(title: "N2", description: "Understanding of some Japanese used in everyday situations and irregular circumstances to some degree")
                    }
                    
                    NavigationLink {
                        JlptListScreen(level: 1)
                    } label: {
                        JlptRow(title: "N1", description: "Understanding od Japanese even in irregular circumstances")
                    }
                }
                .padding()
                
                Spacer()
            }
        }
        .navigationBarBackButtonHidden(true)
        .navigationBarHidden(true)
    }
}
