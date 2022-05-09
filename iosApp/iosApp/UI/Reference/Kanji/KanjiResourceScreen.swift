//
//  KanjiResourceScreen.swift
//  iosApp
//
//  Created by Suraj GP on 09/05/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct KanjiResourceScreen: View {
    
    var body: some View {
        
        ZStack {
            Color.Neomorph.main
                .edgesIgnoringSafeArea(.all)
            
            VStack {
                
                AppTopBar(navigateUpIcon: "arrow.backward", title: "Kanji")
                    .padding()
                
                VStack(alignment: .leading) {
                    Text("Grades")
                        .font(.title)
                        .fontWeight(.semibold)
                        .foregroundColor(Color.Neomorph.text)
                    
                    let columns = [
                        GridItem(.fixed(72), spacing: 24),
                        GridItem(.fixed(72), spacing: 24),
                        GridItem(.fixed(72))
                    ]
                    
                    Text("小学校（1-6）")
                        .font(.title2)
                        .fontWeight(.semibold)
                        .foregroundColor(Color.Neomorph.text)
                    
                    LazyVGrid(columns: columns, spacing: 24) {
                        ForEach(1...6, id: \.self) { grade in
                            KanjiGradeItem(grade: "\(grade)")
                        }
                    }
                    .padding(.vertical)
                    
                    Text("小学校（1-6）")
                        .font(.title2)
                        .fontWeight(.semibold)
                        .foregroundColor(Color.Neomorph.text)
                    
                    LazyVGrid(columns: columns) {
                        ForEach(7...9, id: \.self) { grade in
                            KanjiGradeItem(grade: "\(grade)")
                        }
                    }
                    .padding(.vertical)
                }
                .padding()
                
                Spacer()
            }
        }
        .navigationBarHidden(true)
    }
}
